package com.lightbend.akka.http.sample;

import com.lightbend.akka.http.sample.UserRegistryActor.User;

import java.io.Serializable;

public interface Mensagens {

    class AtualizarDados implements Serializable {
        private final Conta _conta;

        public AtualizarDados(Conta conta) {
            _conta = conta;
        }

        public Conta getConta() {
            return _conta;
        }
    }

    class ConsultarDados implements Serializable {
    }     

    class Retirada implements Serializable {
        private final double _amount;

        public Retirada(double amount) {
            _amount = amount;
        }

        public double getAmount() {
            return _amount;
        }
    }

    class Deposito implements Serializable {
        private final double _amount;

        public Deposito(double amount) {
            _amount = amount;
        }

        public double getAmount() {
            return _amount;
        }
    }

    class SaldoAtualizado implements Serializable {
        private final double _balance;

        public SaldoAtualizado(double balance) {
            _balance = balance;
        }

        public double getBalance() {
            return _balance;
        }
    }

    class LoanCleared implements Serializable {
        private final double _comsumedAmount;

        public LoanCleared(double comsumedAmount) {
            _comsumedAmount = comsumedAmount;
        }

        public double getConsumedAmount() {
            return _comsumedAmount;
        }
    }    
    
    class InsufficientFunds implements Serializable {
    }    

    class GetUsers implements Serializable {
    }

    class ActionPerformed implements Serializable {
        private final String description;

        public ActionPerformed(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    class CreateUser implements Serializable {
        private final User user;

        public CreateUser(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    class GetUser implements Serializable {
        private final String name;

        public GetUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class DeleteUser implements Serializable {
        private final String name;

        public DeleteUser(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}