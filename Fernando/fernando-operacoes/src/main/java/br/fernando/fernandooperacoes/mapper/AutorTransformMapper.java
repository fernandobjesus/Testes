package br.fernando.fernandooperacoes.mapper;


import org.mapstruct.Mapper;

import br.fernando.fernandocommons.DTO.AutorDTO;
import br.fernando.fernandooperacoes.domain.Autor;



@Mapper(componentModel = "spring")
public abstract class AutorTransformMapper {

	public abstract AutorDTO toDTO(Autor autor);
	public abstract Autor toEntity(AutorDTO autorDTO);
	
//	public abstract Autor merge(@MappingTarget Autor autor, AutorDTO autorDTO);

//	@AfterMapping
//	protected void runAfterMapping(@MappingTarget Autor autor) {
////		operacao.populaOperacaoNosTitulos();
////		operacao.somaValoresNaOperacao();
////		operacao.calculaPrazoDosTitulosOperacao();
//	}
}
