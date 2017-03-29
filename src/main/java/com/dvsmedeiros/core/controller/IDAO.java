package com.dvsmedeiros.core.controller;

import java.util.List;

import com.dvsmedeiros.domain.EntidadeDominio;

public interface IDAO<E extends EntidadeDominio> {
	
	public void salvar(E entidade);
	public void alterar(E entidade);
	public void excluir(E entiadade);
	public List<E> cosultar(E entidade);
	public E consultar(long id);
}
