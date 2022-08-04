package br.fernando.fernandocommons.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
@Data	
public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String nome;
	private String email;
	private String descricao;
	private LocalDate data_registro;
}
