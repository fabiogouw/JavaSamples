package hello;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class Lancamento {
	private double _valor;
	private Date _data;
	private String _descricao;
	
	public Lancamento() {
		
	}
	@JsonCreator
	public Lancamento(@JsonProperty("valor") double valor, 
			@JsonProperty("data") Date data, 
			@JsonProperty("descricao") String descricao) {
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

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return MoreObjects.toStringHelper(this)
				.add("valor", _valor)
				.add("data", _data != null ? dateFormat.format(_data) : "")
				.add("descricao", _descricao)
				.toString();
	}
}
