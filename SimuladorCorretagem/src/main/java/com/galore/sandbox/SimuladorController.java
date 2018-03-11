package com.galore.sandbox;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SimuladorController {
	
	@Autowired
	private CalculadoraFactory _calculadoraFactory;
	
	/* Exemplo de JSON para POST
	 * {
	 *     "corretora": "XPTO",
	 *     "operacoes": [
	 *         {
	 *             "papel": "ABCD3",
	 *             "tipoOperacao": "Compra",
	 *             "valorTotal": 10
	 *         }
	 *     ]
	 * } 
	 * */
	
	@PostMapping("/simulador")
	public double post(@RequestBody Simulacao simulacao) {
		Calculadora calculadora = _calculadoraFactory.criar(simulacao.getCorretora());
		return calculadora.calcular(simulacao.getOperacoes());
	}
}
