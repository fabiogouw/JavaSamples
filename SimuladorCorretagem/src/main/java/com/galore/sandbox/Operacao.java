package com.galore.sandbox;

public class Operacao {
	private String _papel;
	private TipoOperacao _tipoOperacao;
	private double _valorTotal;
	
	public Operacao() {
		
	}
	
	public Operacao(String papel, TipoOperacao tipoOperacao, double valorTotal) {
		_papel = papel;
		_tipoOperacao = tipoOperacao;
		_valorTotal = valorTotal;
	}
	
	public String getPapel() {
		return _papel;
	}
	
	public void setPapel(String papel) {
		_papel = papel;
	}
	
	public TipoOperacao getTipoOperacao() {
		return _tipoOperacao;
	}
	
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		_tipoOperacao = tipoOperacao;
	}
	
	public double getValorTotal() {
		return _valorTotal;
	}
	
	public void setValorTotal(double valorTotal) {
		_valorTotal = valorTotal;
	}
}
