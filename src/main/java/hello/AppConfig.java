package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public FonteDados fonteDados() {
		return new FonteDadosEmMemoria();
	}
}
