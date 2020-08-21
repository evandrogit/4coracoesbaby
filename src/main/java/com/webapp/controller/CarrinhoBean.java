package com.webapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.MerchantOrder;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.merchantorder.MerchantOrderPayment;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import com.webapp.mail.model.CustomerInfo;
import com.webapp.mail.model.ProductOrder;
import com.webapp.model.Bairro;
import com.webapp.model.ItemPedido;
import com.webapp.model.Pedido;
import com.webapp.model.Produto;
import com.webapp.repository.Bairros;
import com.webapp.repository.ItensPedidos;
import com.webapp.repository.Pedidos;
import com.webapp.repository.filter.BairroFilter;
import com.webapp.util.jsf.FacesUtil;

@Named
@SessionScoped
public class CarrinhoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto> listaDeProdutos = new ArrayList<Produto>();

	private static final Locale BRAZIL = new Locale("pt", "BR");

	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);

	private NumberFormat nf = new DecimalFormat("###,##0.00", REAL);
	
	private String totalGeralEmString = "R$ 0,00";
	
	private Long totalDeItens = 0L;
	
	private Double totalGeral = 0D;
	
	@Inject
	private Pedido pedido;
	
	@Inject
	private Bairros bairros;
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	private ItensPedidos itensPedidos;
	
	
	private String paymentMethodId;
	
	private Integer Installments;
	
	private Integer token;
	
	private static String TEST_ACCESS_TOKEN = "TEST-1852237905175376-080820-c7142e78a910796fbc26999ef2fa808d-277250128";
	
	private Preference preference;
	
	private String topic;
	
	private String id;

	
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {	
			//System.out.println("*** Teste ***");
		}
	}
	
		
	public void updateStatus() throws MPException {
		
		// Configura credenciais
		MercadoPago.SDK.setAccessToken(TEST_ACCESS_TOKEN);
		
		System.out.println("ID: " + id);
		
		MerchantOrder merchantOrder = null;
		
		if(topic.equals("payment")) {
			Payment payment = Payment.findById(id);
			System.out.println("Payment Status: " + payment.getStatus());
			
			merchantOrder = MerchantOrder.findById(String.valueOf(payment.getOrder().getId()));
		}
		
		if(topic.equals("merchant_order")) {
			merchantOrder = MerchantOrder.findById(id);
		}	
		
		System.out.println("MerchantOrder Status: " + merchantOrder.getStatus());
		
		double paid_amount = 0;
	    for (MerchantOrderPayment payment : merchantOrder.getPayments()) {   
	        if (payment.getStatus().equals("approved")) {
	            paid_amount += payment.getTransactionAmount();
	        }
	    }
	    
	    String observacao = "";

	    // If the payment's transaction amount is equal (or bigger) than the merchant_order's amount you can release your items
	    if(paid_amount >= merchantOrder.getTotalAmount()){
	        if (merchantOrder.getShipments().size() > 0) { // The merchant_order has shipments
	            if(merchantOrder.getShipments().get(0).getStatus().equals("ready_to_ship")) {
	            	observacao = "Totally paid. Print the label and release your item.";
	            }
	        } else { // The merchant_order don't has any shipments
	        	observacao = "Totally paid. Release your item.";
	        }
	    } else {
	    	observacao = "Not paid yet. Do not release your item.";
	    }
	    
	    
	    Pedido pedido = pedidos.porPreferenceId(merchantOrder.getPreferenceId());
	    if(pedido != null) {
	    	pedido.setMerchantOrderId(merchantOrder.getId());
	    	pedido.setStatus(merchantOrder.getStatus());
	    	pedido.setObservacao(observacao);
		    pedidos.save(pedido);
	    }
	 
	}	
	
	
	public void success() throws IOException {
		pedido = new Pedido();		
		listaDeProdutos = new ArrayList<Produto>();		
		atualizarCarrinho();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/catalogo/home.xhtml");
	}
	
	public void pending() throws IOException {
		pedido = new Pedido();		
		listaDeProdutos = new ArrayList<Produto>();		
		atualizarCarrinho();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/catalogo/home.xhtml");
	}
	
	public void failure() throws IOException {		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/catalogo/pagamento.xhtml");
	}
	
	
		
	public List<Bairro> completeText(String query) {
		
        BairroFilter filtro = new BairroFilter();
        filtro.setNome(query);
        
        List<Bairro> listaDeBairros = bairros.filtrados(filtro);       
        
        return listaDeBairros;
    }
	
	
	public void buscaEnderecoPorCEP() throws IOException {
		
		String json;        

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ pedido.getCep() +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
            
            JSONObject jObj = new JSONObject(json);
			pedido.setEndereco(jObj.get("logradouro").toString());
			pedido.setBairro(jObj.get("bairro").toString());
			
			br.close();
            
        } catch (JSONException e) {
        	pedido.setEndereco("");
			pedido.setBairro("");
            //throw new RuntimeException(e);
        }
	}
	
	
	public void pagarComMercadoPago() throws IOException, MPException {

		/*
		 
		Map<String, String> requestParamMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();

		String token = requestParamMap.get("token") != null ? requestParamMap.get("token") : "";


		
		
		MercadoPago.SDK.setAccessToken(TEST_ACCESS_TOKEN);

		Payment payment = new Payment();
		payment.setTransactionAmount(totalGeral.floatValue())
		       .setToken(token)
		       .setDescription("Compras na Decore Web Store")
		       .setInstallments(Installments)
		       .setPaymentMethodId(paymentMethodId)
		       .setPayer(new Payer()
		         .setEmail(pedido.getEmail()));

		payment.save();

		System.out.println(payment.getStatus());
		
		if(payment.getStatus() != null && payment.getStatus().equals(Payment.Status.approved)) {
			
			sendMailAndSavePedido();
			
		} else {
			
			PrimeFaces.current().executeScript(
					"swal({ type: 'error', title: 'Erro!', text: 'Pagamento não realizado.' });");			
		}
		
		
		PrimeFaces.current().ajax().update("form");	
		
		*/
		
		
		Long totalDeItens = 0L;
		Double totalGeral = 0D;
		for (Item item : preference.getItems()) {	
			totalDeItens += item.getQuantity().intValue();
			totalGeral += item.getUnitPrice().doubleValue() * item.getQuantity().intValue();
		}
		
		
		if(this.totalDeItens.intValue() == totalDeItens.intValue() && this.totalGeral.doubleValue() == totalGeral.doubleValue()) {
			sendMailAndSavePedido();
			PrimeFaces.current().executeScript("start__();"); 
			
		} else {
			
			// Configura credenciais
			MercadoPago.SDK.setAccessToken(TEST_ACCESS_TOKEN);	

			// Cria um objeto de preferência
			preference = new Preference();
			
			pedido.setPreferenceId("");

			// Cria um itens na preferência
			for (Produto produto : listaDeProdutos) {
				Item item = new Item();
				item.setTitle(produto.getDescricaoConvertida())
				    .setQuantity(produto.getQuantidadePedido().intValue())
				    .setUnitPrice(produto.getPrecoDeVenda().floatValue());
				preference.appendItem(item);			
			}
			
			com.mercadopago.resources.datastructures.preference.Payer payer = new com.mercadopago.resources.datastructures.preference.Payer()
	        .setEmail(pedido.getEmail());
			preference.setPayer(payer);
				
			preference.save();
						
			PrimeFaces.current().executeScript("stop__();atualizaPedido();");
		}	
		
	}
	
	
	private void sendMailAndSavePedido() {
		
		if(pedido.getId() != null) {
			List<ItemPedido> itensPedido = itensPedidos.porPedido(pedido);
			for (ItemPedido itemPedido : itensPedido) {
				itensPedidos.remove(itemPedido);
			}
		}
		
		
		if(!pedido.getPreferenceId().equals(preference.getId())) {
			pedido.setStatus("AGUARDANDO");
			pedido.setPreferenceId(preference.getId());
		}
			
		
		pedido.setDataPedido(new Date());
		pedido.setQuantidadeItens(totalDeItens);
		pedido.setValorTotal(new BigDecimal(totalGeral));
		
		pedido.setEmail(pedido.getEmail().toLowerCase().trim());
		pedido.setNome(convertToTitleCaseIteratingChars(pedido.getNome().trim()));
		
		pedido.setEmpresa("EMPRESA");
			
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(pedido.getDataPedido());
		
		pedido.setDia(Long.valueOf((calendario.get(Calendar.DAY_OF_MONTH))));
		pedido.setNomeDia(Long.valueOf((calendario.get(Calendar.DAY_OF_WEEK))));
		pedido.setSemana(Long.valueOf((calendario.get(Calendar.WEEK_OF_YEAR))));
		pedido.setMes(Long.valueOf((calendario.get(Calendar.MONTH))) + 1);
		pedido.setAno(Long.valueOf((calendario.get(Calendar.YEAR))));
		
		pedido = pedidos.save(pedido);
		
		for (Produto produto : listaDeProdutos) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(produto.getQuantidadePedido());
			itemPedido.setValorUnitario(produto.getPrecoDeVenda());
			itemPedido.setTotal(new BigDecimal(produto.getQuantidadePedido().intValue() * produto.getPrecoDeVenda().doubleValue()));
			itemPedido.setProduto(produto);
			itemPedido.setPedido(pedido);
			
			itensPedidos.save(itemPedido);
		}
		
		
		
		/*
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		OrderService orderService = (OrderService) context.getBean("orderService");
		
		boolean emailEnviado = orderService.sendOrderConfirmation(getDummyOrder());
		
		((AbstractApplicationContext) context).close();	
		*/
		
		
		
		pedido.setEmailenviado(false);
		pedidos.save(pedido);
		
		
		
		
		
		//pedido = new Pedido();
		
		//listaDeProdutos = new ArrayList<Produto>();
		
		//atualizarCarrinho();
		
		//PrimeFaces.current().executeScript("pedidoEnviado();");
		
	}
	
	
	public void realizarPagamento() throws IOException, MPException {
			
		// Configura credenciais
		MercadoPago.SDK.setAccessToken(TEST_ACCESS_TOKEN);	 

		// Cria um objeto de preferência
		preference = new Preference();
		
		pedido.setPreferenceId("");

		// Cria um itens na preferência
		for (Produto produto : listaDeProdutos) {
			Item item = new Item();
			item.setTitle(produto.getDescricaoConvertida())
			    .setQuantity(produto.getQuantidadePedido().intValue())
			    .setUnitPrice(produto.getPrecoDeVenda().floatValue());
			preference.appendItem(item);			
		}
		
		
		BackUrls backUrls = new BackUrls(
                "https://quatrocoracoesbaby.herokuapp.com/catalogo/success.xhtml",
                "https://quatrocoracoesbaby.herokuapp.com/catalogo/pending.xhtml",
                "https://quatrocoracoesbaby.herokuapp.com/catalogo/failure.xhtml");

		preference.setBackUrls(backUrls);
		
		
		//preference.setAutoReturn(AutoReturn.approved);
		

		PaymentMethods paymentMethods = new PaymentMethods();
		paymentMethods.setInstallments(3);

		preference.setPaymentMethods(paymentMethods);
		
		
		com.mercadopago.resources.datastructures.preference.Payer payer = new com.mercadopago.resources.datastructures.preference.Payer()
        .setEmail(pedido.getEmail());
		preference.setPayer(payer);
			
		preference.save();

		PrimeFaces.current().executeScript("pagamento();");
				
	}
	
	public ProductOrder getDummyOrder()
	{
		ProductOrder order = new ProductOrder();
		order.setOrderId(String.valueOf(pedido.getId()));
		//order.setProductName("Thinkpad Laptop");
		//order.setStatus("Confirmed");
	
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setName(convertToTitleCaseIteratingChars(pedido.getNome().trim()));
		//customerInfo.setAddress("25, West Street, Bangalore");
		customerInfo.setEmail(pedido.getEmail().toLowerCase().trim());
		order.setCustomerInfo(customerInfo);
		
		System.out.println(customerInfo.getName());
		System.out.println(customerInfo.getEmail());
		
		return order;
	}
	
	public void finalizarPedido() throws IOException, MPException {
		
		atualizarCarrinho();
		
		if(totalDeItens > 0) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/catalogo/checkout.xhtml");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/catalogo/carrinho.xhtml");
		}
				
	}
	
	public void atualizarCarrinho() {
		
		List<Produto> produtos = new ArrayList<>();	
		
		totalDeItens = 0L;
		totalGeral = 0D;
		for (Produto produtoTemp : listaDeProdutos) {
	
			if(produtoTemp.getQuantidadePedido() == null || produtoTemp.getQuantidadePedido().intValue() == 0 || produtoTemp.getTotalVendas().doubleValue() == 0) {
				produtos.add(produtoTemp);
			} else {
				produtoTemp.setDescricao(convertToTitleCaseIteratingChars(produtoTemp.getDescricao()));
				totalGeral += produtoTemp.getPrecoDeVenda().doubleValue() * produtoTemp.getQuantidadePedido().intValue();
				totalDeItens += produtoTemp.getQuantidadePedido().intValue();
			}
		}
		
		for (Produto produto : produtos) {
			listaDeProdutos.remove(produto);
		}
		
		totalGeralEmString = nf.format(totalGeral.doubleValue());
		
		try {
			Thread.sleep(1500);
			
		} catch (InterruptedException e) {
		}
	}
	
	public void adicionarNoCarrinho(Produto produto) {
		
		try {
		
			if(!listaDeProdutos.contains(produto)) {
				produto.setQuantidadePedido(produto.getQuantidadePedido());
				listaDeProdutos.add(produto);
			} else {
				Produto produtoTemp = listaDeProdutos.get(listaDeProdutos.indexOf(produto));
				produtoTemp.setQuantidadePedido(produtoTemp.getQuantidadePedido() + produto.getQuantidadePedido());
			}
			
			try {
				Thread.sleep(2000);
				FacesContext.getCurrentInstance().getExternalContext().redirect("/catalogo/carrinho.xhtml");
				
			} catch (InterruptedException e) {
			}
			
			atualizarCarrinho();
			
			totalDeItens = 0L;
			totalGeral = 0D;
			for (Produto produtoTemp : listaDeProdutos) {
				produtoTemp.setDescricao(convertToTitleCaseIteratingChars(produtoTemp.getDescricao()));
				totalGeral += produtoTemp.getPrecoDeVenda().doubleValue() * produtoTemp.getQuantidadePedido().intValue();
				totalDeItens += produtoTemp.getQuantidadePedido().intValue();
			}
			
			totalGeralEmString = nf.format(totalGeral.doubleValue());
			
		} catch(IOException e) {
			
		}
	}
	
	public void removerDoCarrinho(Produto produto) throws IOException {
		Produto produtoTemp = listaDeProdutos.get(listaDeProdutos.indexOf(produto));
		listaDeProdutos.remove(produtoTemp);
		
		totalDeItens = 0L;
		totalGeral = 0D;
		for (Produto produtoTemp_ : listaDeProdutos) {
			produtoTemp_.setDescricao(convertToTitleCaseIteratingChars(produtoTemp_.getDescricao()));
			totalGeral += produtoTemp_.getPrecoDeVenda().doubleValue() * produtoTemp_.getQuantidadePedido().intValue();
			totalDeItens += produtoTemp_.getQuantidadePedido().intValue();
		}
		
		totalGeralEmString = nf.format(totalGeral.doubleValue());
		
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
		}
	}
	
	public String convertToTitleCaseIteratingChars(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }
	 
	    StringBuilder converted = new StringBuilder();
	 
	    boolean convertNext = true;
	    for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch)) {
	            convertNext = true;
	        } else if (convertNext) {
	            ch = Character.toTitleCase(ch);
	            convertNext = false;
	        } else {
	            ch = Character.toLowerCase(ch);
	        }
	        converted.append(ch);
	    }
	 
	    return converted.toString();
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public String getTotalGeralEmString() {
		return totalGeralEmString;
	}

	public Long getTotalDeItens() {
		return totalDeItens;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	public Double getTotalGeral() {
		return totalGeral;
	}

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Integer getInstallments() {
		return Installments;
	}

	public void setInstallments(Integer installments) {
		Installments = installments;
	}

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public Preference getPreference() {
		return preference;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
