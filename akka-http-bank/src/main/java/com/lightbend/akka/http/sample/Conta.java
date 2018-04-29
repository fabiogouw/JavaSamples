package com.lightbend.akka.http.sample;

import java.io.Serializable;

public class Conta implements Serializable {
    private String _identificador;
    private String _correntista;

    public Conta() {

    }

    public Conta(String identificador, String correntista) {
        _identificador = identificador;
        _correntista = correntista;
    }

    public String getIdentificador() {
        return _identificador;
    }

    public String getCorrentista() {
        return _correntista;
    }

    public void setIdentificador(String identificador) {
        _identificador = identificador;
    }

    public void setCorrentista(String correntista) {
        _correntista = correntista;
    }    
}