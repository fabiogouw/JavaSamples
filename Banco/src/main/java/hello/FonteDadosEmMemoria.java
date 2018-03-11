package hello;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class FonteDadosEmMemoria implements FonteDados {

	private static List<Lancamento> _lancamentos = new ArrayList<Lancamento>();
	
	@Override
	public Lancamento[] listar() {
		Lancamento[] lancamentos = new Lancamento[_lancamentos.size()];
		lancamentos = _lancamentos.toArray(lancamentos);
		return lancamentos;
	}

	@Override
	public Lancamento incluir(Lancamento lancamento) {
		_lancamentos.add(lancamento);
		return lancamento;
	}
}
