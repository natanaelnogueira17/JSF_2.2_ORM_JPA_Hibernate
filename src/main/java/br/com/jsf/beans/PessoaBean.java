package br.com.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.jsf.dao.DaoGeneric;
import br.com.jsf.entidades.Pessoa;
import br.com.jsf.repository.PessoaDAO;
import br.com.jsf.repository.PessoaDaoImpl;

@ViewScoped
@ManagedBean
public class PessoaBean {
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<>();
	private List<Pessoa> pessoas = new ArrayList <>();
	private boolean  novo = true;
	private PessoaDAO pessoaDao = new PessoaDaoImpl();
	
	public String logar() {
		Pessoa pessoaUser = pessoaDao.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		if(pessoaUser != null) {//achou o usuario
			//add usuario na sessao usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();//para qualquer informação do ambiente de execução do jSF
			ExternalContext externalContext = context.getExternalContext();
			
			HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();			
			HttpSession session = req.getSession();
			session.setAttribute("usuarioLogado", pessoaUser);
			//RequestDispatcher dispatcher = req.getRequestDispatcher("/usuarios.xhtml");			
			return "usuarios.xhtml";
		}
		return "login.xhtml";
	}
	
	public boolean permitirAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();//para qualquer informação do ambiente de execução do jSF
		ExternalContext externalContext = context.getExternalContext(); 
		Pessoa pessoaUser = (Pessoa)externalContext.getSessionMap().get("usuarioLogado"); // só da acesso se a sessao for setada com usuarioLogado
		return pessoaUser.getPerfilUser().equals(acesso); //retorna o acesso é usuarioLogado
	}

	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		novoUser();
		return "";
	}

	public String novoUser() {
		pessoa = new Pessoa();
		novo = true;
		return "";
	}

	public String remove() {
		daoGeneric.deletePorID(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		return "";
	}
	
	@PostConstruct
	public void carregarPessoas() {
		pessoas =  daoGeneric.getListEntity(Pessoa.class);
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}
	
	

}
