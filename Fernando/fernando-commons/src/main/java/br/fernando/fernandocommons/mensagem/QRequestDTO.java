package br.fernando.fernandocommons.mensagem;

import java.io.Serializable;

import lombok.Data;

@Data
public class QRequestDTO implements Serializable {

	private static final long serialVersionUID = 5277553801366314342L;
	private Object objeto;
	private CrudMethod crudMethod;
}
