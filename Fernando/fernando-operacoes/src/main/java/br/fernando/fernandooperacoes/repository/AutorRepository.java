package br.fernando.fernandooperacoes.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fernando.fernandooperacoes.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID>{

}
