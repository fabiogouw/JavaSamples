package hello;

import java.util.Date;

public class FonteDadosEmMemoria implements FonteDados {

	private static Lancamento[] _lancamentos = new Lancamento[] {
			new Lancamento(1000, new Date(), "teste")
	};
	
	@Override
	public Lancamento[] listar() {
		return _lancamentos;
	}

}
