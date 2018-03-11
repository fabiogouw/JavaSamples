package com.galore.sandbox;

public class CalculadoraFactoryImpl implements CalculadoraFactory {
	public Calculadora criar(String corretora) {
		return new CalculadoraTabelaBovespa();
	}
}
