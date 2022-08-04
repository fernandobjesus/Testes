package br.fernando.fernandoapi.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.fernando.fernandocommons.mensagem.QRequestDTO;
import br.fernando.fernandocommons.mensagem.QResponseDTO;

@Service
public class AutorSender {

	@Autowired
	private RabbitTemplate template;

	@Value("${thanos.direct.exchange.estudos.operacao}")
	private String directExchange;
	
	QRequestDTO request = new QRequestDTO();
	
	public QResponseDTO enviaAutorSincrono(QRequestDTO request) throws Exception{
		QResponseDTO response = new QResponseDTO();
		try {
			template.convertAndSend(directExchange, "filaAutorQueue", request);
			response.setErro(false);
			response.setMensagemRetorno("Enviado Autor no modo Sincrono.");
			return response;
		} catch (Exception e) {
			response.setErro(true);
			response.setMensagemRetorno("Erro no envio do autor.");
			response.setObjeto(e);
			return response;
		}	
	}

	public QResponseDTO enviaAutor(QRequestDTO request) throws Exception{
		QResponseDTO response = new QResponseDTO();
		try {
			response = (QResponseDTO) template.convertSendAndReceive(directExchange, "filaAutorQueue", request);
			response.setErro(false);
			return response;
		} catch (Exception e) {
			response.setErro(true);
			response.setMensagemRetorno("Erro no envio do autor.");
			return response;
		}	
	}


}
