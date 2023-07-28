package br.com.jsf.repository;

import br.com.jsf.entidades.Pessoa;

public interface PessoaDAO {
	
	
	Pessoa consultarUsuario(String login, String senha);
}
