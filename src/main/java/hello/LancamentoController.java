package hello;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LancamentoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LancamentoController.class);
	private static int _count = 0;

	@Autowired
	private FonteDados _fonteDados;
	@Autowired
	private KafkaTemplate<String, String> _kafkaTemplate;	
	
	@RequestMapping(value = "/transacao", method = RequestMethod.GET)
	public Lancamento[] index() {
		return _fonteDados.listar();
	}
	
	@RequestMapping(value = "/transacao", method = RequestMethod.POST)
	public Lancamento criarLancamento(@RequestBody Lancamento lancamento) {
		try {
			if(lancamento != null) {
				lancamento.setData(new Date());
				_fonteDados.incluir(lancamento);
				_kafkaTemplate.send("transacoes", lancamento.toString());
			}
			return lancamento;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@KafkaListener(topics = "transacoes")
	public void receive(String payload) {
		LOGGER.info("received payload = '{}'", payload);
		_count++;
	}
	
	@RequestMapping(value = "/kafka", method = RequestMethod.GET)
	public int getTotal() {
		return _count;
	}	
}
