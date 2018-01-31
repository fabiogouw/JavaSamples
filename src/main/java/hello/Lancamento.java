package hello;

import java.util.Date;

public class Lancamento {
	private double _valor;
	private Date _data;
	private String _descricao;
	
	public Lancamento() {
		
	}
	
	public Lancamento(double valor, Date data, String descricao) {
		_valor = valor;
		_data = data;
		_descricao = descricao;
	}
	
	public void setValor(double valor) {
		_valor = valor;
	}
	public double getValor() {
		return _valor;
	}
	
	public void setData(Date data) {
		_data = data;
	}
	public Date getData() {
		return _data;
	}
	
	public void setDescricao(String descricao) {
		_descricao = descricao;
	}
	public String getDescricao() {
		return _descricao;
	}	
}
