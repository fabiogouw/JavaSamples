package com.galore.sandbox;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculadoraTabelaBovespaTest {
	@Test
	public void deveCalcularCorretagemZerada() {
		Calculadora calculadora = new CalculadoraTabelaBovespa();
		double retorno = calculadora.calcular(new Operacao[] {});
		assertEquals(0, retorno, 0.01);
	}
	
	@Test
	public void deveCalcularCorretagemLimiteSuperiorPrimeiraFaixa() {
		Calculadora calculadora = new CalculadoraTabelaBovespa();
		double retorno = calculadora.calcular(new Operacao[] { 
			new Operacao("ABCD3", TipoOperacao.Compra, 135.07)
		});
		assertEquals(2.70, retorno, 0.01);
	}
}
