package com.galore.sandbox;

public class CalculadoraTabelaBovespa implements Calculadora {
	public double calcular(Operacao[] operacoes) {
		if(operacoes.length > 0) {
			return 2.70;
		}
		return 0;
		// TODO: complementar e refatorar esta calculadora
	}
}
