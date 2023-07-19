package br.com.jsf;

import javax.persistence.Persistence;

public class TesteJpa {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("JSF");
		

	}

}
