package br.fernando.fernandooperacoes.mapper;

import br.fernando.fernandocommons.DTO.AutorDTO;
import br.fernando.fernandooperacoes.domain.Autor;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-04T18:16:26-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class AutorTransformMapperImpl extends AutorTransformMapper {

    @Override
    public AutorDTO toDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setData_registro( autor.getData_registro() );
        autorDTO.setDescricao( autor.getDescricao() );
        autorDTO.setEmail( autor.getEmail() );
        autorDTO.setId( autor.getId() );
        autorDTO.setNome( autor.getNome() );

        return autorDTO;
    }

    @Override
    public Autor toEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setData_registro( autorDTO.getData_registro() );
        autor.setDescricao( autorDTO.getDescricao() );
        autor.setEmail( autorDTO.getEmail() );
        autor.setId( autorDTO.getId() );
        autor.setNome( autorDTO.getNome() );

        return autor;
    }
}
