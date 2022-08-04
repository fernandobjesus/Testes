package br.fernando.fernandooperacoes.queue.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.fernando.fernandocommons.DTO.AutorDTO;
import br.fernando.fernandocommons.mensagem.QRequestDTO;
import br.fernando.fernandocommons.mensagem.QResponseDTO;
import br.fernando.fernandooperacoes.service.AutorService;


import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AutorListener {
	
	@Autowired
	AutorService autorService;
	
	@RabbitListener(queues = "{$thanos.fila.autor.queue}")
	public QResponseDTO processaAutor(QRequestDTO request) {
		QResponseDTO response = new QResponseDTO();
		
		switch(request.getCrudMethod()) {
		case LIST:
			log.info("Listener: LIST");
			break;
		case GET:
			log.info("Listener: GET");
			break;
		case INSERT:
			log.info("Listener: INSERT");
			try {
				AutorDTO autorCadastrado = (AutorDTO) request.getObjeto();
				autorService.saveAutor(autorCadastrado);
				response.setErro(false);
				response.setMensagemRetorno("Autor salvo com sucesso.");
				response.setObjeto(autorCadastrado);
				log.error("Falha ao cadastrar usuário: ", response);
			} catch (Exception e) {
				response.setErro(true);
				response.setMensagemRetorno("Erro ao cadastrar o autor.");
				response.setObjeto(e);
				log.error("Falha ao cadastrar usuário: ", e.getMessage());
			}
			break;
		case DELETE:
			log.info("Listener: DELETE");
			break;
		default:
			break;
		}
		return response;
	}

}

