package com.lightbend.akka.http.sample;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

import java.util.*;
import java.io.Serializable;

public class ContaActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static Props props(String identificador/*, SaldoCorrenteActor saldoActor, SaldoLimiteActor saldoLimite*/) {
        return Props.create(ContaActor.class, identificador);
    }

    private final Conta _conta = new Conta();
    private SaldoCorrenteActor _saldoActor;
    private SaldoLimiteActor _saldoLimite;

    public ContaActor(String identificador) {
        _conta.setIdentificador(identificador);
        //_saldoActor = saldoActor;
        //_saldoLimite = saldoLimite;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Mensagens.AtualizarDados.class, atualizacao -> {
                _conta.setIdentificador(atualizacao.getConta().getIdentificador());
                getSender().tell(_conta, getSelf());
            })
            .match(Mensagens.ConsultarDados.class, atualizacao -> {
                getSender().tell(_conta, getSelf());
            })            
            .match(Mensagens.Deposito.class, deposit -> {
                double amount = deposit.getAmount();
                //_balance += amount;
                getSender().tell(new Mensagens.SaldoAtualizado(amount), getSelf());
            })
            .match(Mensagens.Retirada.class, deposit -> {
                double amount = deposit.getAmount();
                /*if(_balance - amount < 0 ){
                    getSender().tell(new Mensagens.InsufficientFunds(), getSelf());
                }
                else {
                    _balance -= amount;
                    getSender().tell(new Mensagens.SaldoAtualizado(_balance), getSelf());
                }*/
            })            
                /*.match(UserRegistryMessages.GetUsers.class, getUsers -> getSender().tell(new Users(users),getSelf()))
                .match(UserRegistryMessages.CreateUser.class, createUser -> {
                  users.add(createUser.getUser());
                  getSender().tell(new UserRegistryMessages.ActionPerformed(
                          String.format("User %s created.", createUser.getUser().getName())),getSelf());
                })
                .match(UserRegistryMessages.GetUser.class, getUser -> {
                  getSender().tell(users.stream()
                          .filter(user -> user.getName().equals(getUser.getName()))
                          .findFirst(), getSelf());
                })
                .match(UserRegistryMessages.DeleteUser.class, deleteUser -> {
                  users.removeIf(user -> user.getName().equals(deleteUser.getName()));
                  getSender().tell(new UserRegistryMessages.ActionPerformed(String.format("User %s deleted.", deleteUser.getName())),
                          getSelf());
                
                }).matchAny(o -> log.info("received unknown message"))*/
            .build();
    }
}
