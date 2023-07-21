package br.com.jsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jsf.dao.DaoGeneric;
import br.com.jsf.entidades.Pessoa;

@ViewScoped
@ManagedBean
public class PessoaBean {
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa>daoGeneric = new DaoGeneric<>();
	
	public String salvar () {		
		pessoa = daoGeneric.merge(pessoa);
		return "";
	}
	
	public String novoUser() {
		pessoa =  new Pessoa();
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	
	
}
