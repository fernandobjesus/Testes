package br.fernando.fernandocommons.mensagem;

import java.io.Serializable;

import lombok.Data;

@Data
public class QResponseDTO implements Serializable{
	
	private static final long serialVersionUID = -92711001580885805L;

	private String mensagemRetorno;
	private boolean erro;
	private Object objeto;

}
