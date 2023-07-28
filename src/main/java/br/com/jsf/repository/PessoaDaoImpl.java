package br.com.jsf.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jsf.entidades.Pessoa;
import br.com.jsf.jpautil.JPAUtil;

public class PessoaDaoImpl implements PessoaDAO {

	@Override
	public Pessoa consultarUsuario(String login, String senha) {
		Pessoa pessoa = null;		
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction =  entityManager.getTransaction();
		transaction.begin();
		pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p.login = '" +login+"' and p.senha = '"+senha+"'").getSingleResult();
		
		transaction.commit();
		entityManager.close();
		return pessoa;
	}

}
