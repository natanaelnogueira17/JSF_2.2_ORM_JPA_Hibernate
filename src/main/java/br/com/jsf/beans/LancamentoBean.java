package br.com.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.jsf.dao.DaoGeneric;
import br.com.jsf.entidades.Lancamento;
import br.com.jsf.entidades.Pessoa;

@ViewScoped
@ManagedBean
public class LancamentoBean {
	private Lancamento lancamento = new Lancamento();
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<>();
	private List<Lancamento> lancamentos = new ArrayList<>();
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();//para qualquer informação do ambiente de execução do jSF
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		lancamento.setUsuario(pessoaUser);
		daoGeneric.salvar(lancamento);
		
		
		return "";
	}
	
	public String novo(){
		return "";
	}
	public String remover() {
		return "";
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public DaoGeneric<Lancamento> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Lancamento> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
