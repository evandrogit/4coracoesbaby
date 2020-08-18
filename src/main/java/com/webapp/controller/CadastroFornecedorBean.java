package com.webapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.webapp.model.Fornecedor;
import com.webapp.model.Grupo;
import com.webapp.model.Usuario;
import com.webapp.repository.Fornecedores;
import com.webapp.repository.Usuarios;
import com.webapp.repository.filter.FornecedorFilter;
import com.webapp.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Fornecedores fornecedores;

	@Inject
	private Fornecedor fornecedor;

	private List<Fornecedor> todosFornecedores;

	private Fornecedor fornecedorSelecionado;

	private FornecedorFilter filtro = new FornecedorFilter();
	
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
		fornecedor = new Fornecedor();
	}

	public void prepararEditarCadastro() {
		fornecedor = fornecedores.porId(fornecedorSelecionado.getId());
	}

	public void salvar() {
		
			
		if (fornecedor.getId() == null) {
			
			Fornecedor fornecedorTemp = fornecedores.porNome(fornecedor.getNome(), usuario.getEmpresa());
		
			if(fornecedorTemp == null) {
				
				fornecedor.setEmpresa(usuario.getEmpresa());
	
				fornecedores.save(fornecedor);
	
				fornecedorSelecionado = null;
	
				listarTodos(); 
	
				PrimeFaces.current().executeScript(
						"PF('downloadLoading').hide();PF('fornecedor-dialog').hide();swal({ type: 'success', title: 'Concluído!', text: 'Fornecedor salvo com sucesso!' });");
	
				PrimeFaces.current().ajax().update("form");
				
			} else {
						
				PrimeFaces.current().executeScript(
						"swal({ type: 'error', title: 'Erro!', text: 'Já existe um fornecedor com o nome informado!' });");
			}
		
		} else {
			
			Fornecedor fornecedor = fornecedores.porNomeCadastrado(this.fornecedor, usuario.getEmpresa());
			
			if(fornecedor == null) {
				
				this.fornecedor.setEmpresa(usuario.getEmpresa());
				
				fornecedores.save(this.fornecedor);
	
				fornecedorSelecionado = null;
	
				listarTodos(); 
	
				PrimeFaces.current().executeScript(
						"PF('downloadLoading').hide();PF('fornecedor-dialog').hide(); swal({ type: 'success', title: 'Concluído!', text: 'Fornecedor atualizado com sucesso!' });");

				PrimeFaces.current().ajax().update("form");
				
			} else {
				PrimeFaces.current().executeScript(
						"swal({ type: 'error', title: 'Erro!', text: 'Já existe um fornecedor com o nome informado!' });");	
			}
			
		}
		
	}

	public void excluir() {

		fornecedores.remove(fornecedorSelecionado);

		fornecedorSelecionado = null;

		pesquisar();

		PrimeFaces.current().executeScript(
				"swal({ type: 'success', title: 'Concluído!', text: 'Fornecedor excluído com sucesso!' });");

	}

	public void pesquisar() {
		filtro.setEmpresa(usuario.getEmpresa());
		todosFornecedores = fornecedores.filtrados(filtro);
		fornecedorSelecionado = null;
	}

	private void listarTodos() {
		todosFornecedores = fornecedores.todos(usuario.getEmpresa());
	}

	public List<Fornecedor> getTodosFornecedores() {
		return todosFornecedores;
	}

	public FornecedorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(FornecedorFilter filtro) {
		this.filtro = filtro;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fonecedor) {
		this.fornecedor = fonecedor;
	}

}