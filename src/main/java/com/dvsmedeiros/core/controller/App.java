package com.dvsmedeiros.core.controller;

import java.util.List;

import com.dvsmedeiros.domain.Pessoa;

public class App {
	
	public static void main(String[] args) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(2L);
		
		new PessoaDAO().excluir(pessoa);
		
		
		
	}
}
