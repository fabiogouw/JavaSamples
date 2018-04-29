package com.lightbend.akka.http.sample;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SaldoLimiteActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static Props props() {
        return Props.create(SaldoLimiteActor.class);
    }

    private double _limite;
    private double _saldo;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Mensagens.Deposito.class, deposit -> {
                double saldoDeposito = deposit.getAmount();
                _saldo -= saldoDeposito;
                getSender().tell(new Mensagens.SaldoAtualizado(_saldo), getSelf());
            })
            .match(Mensagens.Retirada.class, deposit -> {
                double valorRetirada = deposit.getAmount();
                double limiteDisponivel = _limite - _saldo;
                if(limiteDisponivel - valorRetirada < 0){
                    getSender().tell(new Mensagens.InsufficientFunds(), getSelf());
                }
                else {
                    _saldo += valorRetirada;
                    getSender().tell(new Mensagens.SaldoAtualizado(_saldo), getSelf());
                }
            })
            .build();
    }
}
