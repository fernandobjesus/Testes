package br.fernando.fernandoapi.controller;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fernando.fernandoapi.sender.AutorSender;
import br.fernando.fernandocommons.DTO.AutorDTO;
import br.fernando.fernandocommons.mensagem.CrudMethod;
import br.fernando.fernandocommons.mensagem.QRequestDTO;
import br.fernando.fernandocommons.mensagem.QResponseDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/v1")
public class AutorController{
	
	@Autowired
	private AutorSender autorSender;
		
	@PostMapping(value = "/public/cadastra/Autor/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QResponseDTO> enviaAutor(@RequestBody AutorDTO autorDTO){
		QResponseDTO response = new QResponseDTO();
		try {
			QRequestDTO request = new QRequestDTO();
			request.setObjeto(autorDTO);
			request.setCrudMethod(CrudMethod.INSERT);
			
			response.setObjeto(autorSender.enviaAutor(request));
			response.setMensagemRetorno("Controller Autor: solicitação de cadastramento");
			response.setErro(false);
			log.info(response);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setMensagemRetorno("Erro ao enviar operação para o RabbEitMQ");
			response.setErro(true);
			response.setObjeto(e);
			log.info(response.getMensagemRetorno(), e.getMessage());
			return ResponseEntity.ok(response);
		}
	}
	
	@PostMapping(value = "/public/lista/Autor/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QResponseDTO> listaTodasOperacoes(){
		QResponseDTO response = new QResponseDTO();
		try {
			QRequestDTO request = new QRequestDTO();
			request.setCrudMethod(CrudMethod.LIST);
			
			response.setObjeto(autorSender.enviaAutor(request));
			response.setMensagemRetorno("Controller Autor: solicitação de listagem");
			log.info(response);
		} catch (Exception e) {
			response.setErro(true);
			response.setMensagemRetorno("Controller Autor: erro na listagem");
			response.setObjeto(e);
			log.info(response.getMensagemRetorno(), e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/public/atualiza/Autor/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QResponseDTO> alteraAutor(@RequestBody AutorDTO AutorDTO){
		QResponseDTO response = new QResponseDTO();
		try {
			QRequestDTO request = new QRequestDTO();
			request.setObjeto(AutorDTO);
			request.setCrudMethod(CrudMethod.LIST);
			
			response.setObjeto(autorSender.enviaAutor(request));
			response.setMensagemRetorno("Controller Autor: solicitação de atualização");	
			response.setErro(false);
			log.info(response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setObjeto(e);
			response.setMensagemRetorno("Controler Autor: erro na atualização");
			response.setErro(true);
			log.info(response.getMensagemRetorno(), e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/public/exclui/Autor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QResponseDTO> excluiAutor(@PathVariable UUID id){
		QResponseDTO response = new QResponseDTO();
		try {
			QRequestDTO request = new QRequestDTO();
			request.setObjeto(id);
			request.setCrudMethod(CrudMethod.DELETE);
			
			response.setObjeto(autorSender.enviaAutor(request));
			response.setMensagemRetorno("Controler Autor: solicitação de exclusão");
			response.setErro(false);
			log.info(response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensagemRetorno("Controller Autor: erro na exclusão");
			response.setErro(true);
			response.setObjeto(e);
			log.info(response.getMensagemRetorno(), e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/public/buscar/Autor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QResponseDTO> buscaAutor(@PathVariable UUID id){
		QResponseDTO response = new QResponseDTO();
		try {
			QRequestDTO request = new QRequestDTO();
			request.setCrudMethod(CrudMethod.GET);
			request.setObjeto(id);
			
			response.setObjeto(autorSender.enviaAutor(request));
			response.setMensagemRetorno("Controller Autor: solicitação de busca");
			response.setErro(false);
			log.info(response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setMensagemRetorno("Controller Autor: erro na busca");
			response.setErro(true);
			response.setObjeto(e);
			log.info(response.getMensagemRetorno(), e.getMessage());			
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}