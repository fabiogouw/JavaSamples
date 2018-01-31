package hello;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class LancamentoController {
	
	private final FonteDados _fonteDados;
	
	public LancamentoController(FonteDados fonteDados) {
		_fonteDados = fonteDados;
	}
	
	@RequestMapping("/")
	public Lancamento[] index() {
		return _fonteDados.listar();
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return "Greetings from Spring Boot built with Gradle (v2): " + dateFormat.format(date);*/ 
	}
}
