package br.com.jsf.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jsf.jpautil.JPAUtil;

public class DaoGeneric <E>{
	public void salvar(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction =  entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(entidade);
		transaction.commit();
		entityManager.close();
	}
	
	
	public E  merge(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction =  entityManager.getTransaction();
		transaction.begin();
		E retorno =  entityManager.merge(entidade);		
		transaction.commit();
		entityManager.close();
		
		return retorno;
	}
	
	public void delete(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction =  entityManager.getTransaction();
		transaction.begin();
		Object id =  JPAUtil.getPrimaryKey(entidade);
		entityManager.createNamedQuery("delete from "+entidade.getClass().getCanonicalName() + " where id = "+ id).executeUpdate();
		entityManager.remove(entidade);
		transaction.commit();
		entityManager.close();
	}
}
