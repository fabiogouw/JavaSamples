package com.lightbend.akka.http.sample;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SaldoCorrenteActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static Props props() {
        return Props.create(SaldoCorrenteActor.class);
    }

    private double _saldo;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Mensagens.Deposito.class, deposit -> {
                double valorDeposito = deposit.getAmount();
                _saldo += valorDeposito;
                getSender().tell(new Mensagens.SaldoAtualizado(_saldo), getSelf());
            })
            .match(Mensagens.Retirada.class, deposit -> {
                double valorDeposito = deposit.getAmount();
                if(_saldo - valorDeposito < 0 ){
                    getSender().tell(new Mensagens.InsufficientFunds(), getSelf());
                }
                else {
                    _saldo -= valorDeposito;
                    getSender().tell(new Mensagens.SaldoAtualizado(_saldo), getSelf());
                }
            })
            .build();
    }
}
