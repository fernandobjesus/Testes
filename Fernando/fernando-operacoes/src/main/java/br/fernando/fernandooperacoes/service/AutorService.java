package br.fernando.fernandooperacoes.service;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fernando.fernandocommons.DTO.AutorDTO;
import br.fernando.fernandooperacoes.domain.Autor;
import br.fernando.fernandooperacoes.mapper.AutorTransformMapper;
import br.fernando.fernandooperacoes.repository.AutorRepository;


import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class AutorService {

	@Autowired
	AutorRepository autorRepository;
	
	@Autowired
	AutorTransformMapper mapper;
	
	public AutorDTO saveAutor(AutorDTO autorDTO) {
		Autor autor = new Autor();
		Optional<Autor> oAutor = autorRepository.findById(autorDTO.getId());
		try {
			if(oAutor.isPresent()) {
				autor = oAutor.get();
				autorRepository.save(autor);
			}else {
//			validaEmail(autor.getEmail());
				autorRepository.save(autor);
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mapper.toDTO(autor);
	}
	
	private List<String> validaEmail(String emails) {
//		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		final String EMAIL_PATTERN = "^[A-Z0-9a-z-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		try {
			List<String> listaEmails = null;

			if (emails.contains(";"))
				listaEmails = Arrays.asList(emails.split(";"));
			else
				listaEmails = Arrays.asList(emails);

			return listaEmails.stream().filter(email -> {
				if(Objects.nonNull(email)) {
					Matcher matcher = pattern.matcher(email);
					return matcher.matches();
				}else {
					return false;
				}
				
			}).collect(Collectors.toList());
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	public AutorDTO buscaAutor(UUID id) {
		Optional<Autor> oAutor = autorRepository.findById(id);
		AutorDTO autor = new AutorDTO();
		if(oAutor.isPresent()) {
			autor = mapper.toDTO(oAutor.get());     			
			return autor;
		}else {
			return autor;
		}
	}
	
	void excluiAutor(UUID id) {
		Optional<Autor> oAutor = autorRepository.findById(id);
		if(oAutor.isPresent()) {
			autorRepository.deleteById(id);
		}else {
			log.error("Não existe o autor informado.");
		}
	}
	
}

