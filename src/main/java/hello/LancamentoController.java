package hello;

import java.util.Date;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
public class LancamentoController {
	
	@Autowired
	private FonteDados _fonteDados;
	@Autowired
	private KafkaTemplate<String, String> _kafkaTemplate;	
	
	@RequestMapping(value = "/transacao", method = RequestMethod.GET)
	public Lancamento[] index() {
		return _fonteDados.listar();
	}
	
	@RequestMapping(value = "/transacao", method = RequestMethod.POST)
	public Lancamento criarLancamento(Lancamento lancamento) {
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
}
