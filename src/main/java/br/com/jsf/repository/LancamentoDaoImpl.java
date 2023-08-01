package br.com.jsf.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jsf.jpautil.JPAUtil;
import br.com.jsf.entidades.Lancamento;

public class LancamentoDaoImpl implements LancamentoDao{

	@Override
	public List<Lancamento> consultar(Long userId) {
		List<Lancamento> lista = null;
		EntityManager entityManager =  JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		lista = entityManager.createQuery("from Lancamento where usuario.id = " +userId).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		
		return lista;
	}
	
}
	
