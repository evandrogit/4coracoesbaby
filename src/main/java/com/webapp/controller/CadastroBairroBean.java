package com.webapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.webapp.model.Bairro;
import com.webapp.model.Grupo;
import com.webapp.model.Usuario;
import com.webapp.model.Zona;
import com.webapp.repository.Bairros;
import com.webapp.repository.Usuarios;
import com.webapp.repository.filter.BairroFilter;
import com.webapp.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBairroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Bairros bairros;

	@Inject
	private Bairro bairro;

	private List<Bairro> todosBairros;

	private Bairro bairroSelecionado;

	private BairroFilter filtro = new BairroFilter();
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private Usuarios usuarios;
	

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
			usuario = usuarios.porNome(user.getUsername());
			
			List<Grupo> grupos = usuario.getGrupos();
			
			if(grupos.size() > 0) {
				for (Grupo grupo : grupos) {
					if(grupo.getNome().equals("ADMINISTRADOR")) {
						EmpresaBean empresaBean = (EmpresaBean) FacesUtil.getObjectSession("empresaBean");
						if(empresaBean != null && empresaBean.getEmpresa() != null) {
							usuario.setEmpresa(empresaBean.getEmpresa());
						}
					}
				}
			}
			
			listarTodos();
		}
	}

	public void prepararNovoCadastro() {
		bairro = new Bairro();
	}

	public void prepararEditarCadastro() {
		bairro = bairros.porId(bairroSelecionado.getId());
	}

	public void salvar() {
		
		if (bairro.getId() == null) {
			
			Bairro bairroTemp = bairros.porNome(bairro.getNome());
		
			if(bairroTemp == null) {
	
				bairros.save(bairro);
	
				bairroSelecionado = null;
	
				listarTodos(); 
				
				PrimeFaces.current().executeScript(
						"PF('downloadLoading').hide(); PF('bairro-dialog').hide(); swal({ type: 'success', title: 'Concluído!', text: 'Bairro salvo com sucesso!' });");
				
				PrimeFaces.current().ajax().update("form");
				
			} else {
						
				PrimeFaces.current().executeScript(
						"swal({ type: 'error', title: 'Erro!', text: 'Já existe um bairro com o nome informado!' });");
			}
		
		} else {
			
			Bairro bairro = bairros.porNomeCadastrado(this.bairro);
			
			if(bairro == null) {
				
				bairros.save(this.bairro);
	
				bairroSelecionado = null;
	
				listarTodos(); 
	
				PrimeFaces.current().executeScript(
						"PF('downloadLoading').hide();PF('bairro-dialog').hide(); swal({ type: 'success', title: 'Concluído!', text: 'Bairro atualizado com sucesso!' });");
				
				PrimeFaces.current().ajax().update("form");
				
			} else {
				PrimeFaces.current().executeScript(
						"swal({ type: 'error', title: 'Erro!', text: 'Já existe um bairro com o nome informado!' });");	
			}
			
		}
	}

	public void excluir() {

		bairros.remove(bairroSelecionado);

		bairroSelecionado = null;

		pesquisar();

		PrimeFaces.current().executeScript(
				"swal({ type: 'success', title: 'Concluído!', text: 'Bairro excluído com sucesso!' });");

	}

	public void pesquisar() {
		todosBairros = bairros.filtrados(filtro);
		bairroSelecionado = null;
	}

	private void listarTodos() {
		todosBairros = bairros.todos();
	}

	public List<Bairro> getTodosBairros() {
		return todosBairros;
	}

	public BairroFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(BairroFilter filtro) {
		this.filtro = filtro;
	}

	public Bairro getBairroSelecionado() {
		return bairroSelecionado;
	}

	public void setBairroSelecionado(Bairro bairroSelecionado) {
		this.bairroSelecionado = bairroSelecionado;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	public Zona[] getZonas() {
		return Zona.values();
	}

}