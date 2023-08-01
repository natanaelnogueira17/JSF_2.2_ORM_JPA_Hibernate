package br.com.jsf.repository;

import java.util.List;

import br.com.jsf.entidades.Lancamento;

public interface LancamentoDao {
	List<Lancamento> consultar (Long userId);
}
