package br.fernando.fernandooperacoes.mapper;

import br.fernando.fernandocommons.DTO.AutorDTO;
import br.fernando.fernandooperacoes.domain.Autor;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-04T13:12:36-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_332 (Azul Systems, Inc.)"
)
@Component
public class AutorTransformMapperImpl extends AutorTransformMapper {

    @Override
    public AutorDTO toDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setId( autor.getId() );
        autorDTO.setNome( autor.getNome() );
        autorDTO.setEmail( autor.getEmail() );
        autorDTO.setDescricao( autor.getDescricao() );
        autorDTO.setData_registro( autor.getData_registro() );

        return autorDTO;
    }

    @Override
    public Autor toEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setId( autorDTO.getId() );
        autor.setNome( autorDTO.getNome() );
        autor.setEmail( autorDTO.getEmail() );
        autor.setDescricao( autorDTO.getDescricao() );
        autor.setData_registro( autorDTO.getData_registro() );

        return autor;
    }
}
