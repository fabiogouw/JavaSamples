package com.lightbend.akka.http.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import com.lightbend.akka.http.sample.UserRegistryActor.User;
import com.lightbend.akka.http.sample.Mensagens.ActionPerformed;
import com.lightbend.akka.http.sample.Mensagens.CreateUser;
import scala.concurrent.duration.Duration;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

/**
 * Routes can be defined in separated classes like shown in here
 */
//#user-routes-class
public class UserRoutes extends AllDirectives {
    //#user-routes-class
    final private LoggingAdapter log;
    final private ActorSystem _system;

    public UserRoutes(ActorSystem system) {
        log = Logging.getLogger(system, this);
        _system = system;
    }

    // Required by the `ask` (?) method below
    Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS)); // usually we'd obtain the timeout from the system's configuration

    /**
     * This method creates one route (of possibly many more that will be part of your Web App)
     */
    //#all-routes
    //#users-get-delete
    public Route routes() {
        return route(pathPrefix("contas",
                () -> route(getOrPostUsers(), path(PathMatchers.segment(), name -> route(getUser(name))))));
    }
    //#all-routes

    //#users-get-delete

    //#users-get-delete
    private Route getUser(String name) {
        return get(() -> {
            ActorSelection s = _system.actorSelection("/user/" + name);
            ActorRef contaActor = s.resolveOne(new Timeout(5, TimeUnit.SECONDS)).value().get().get();
            if (contaActor.isTerminated()) {
                return complete(StatusCodes.BAD_REQUEST);
            } else {
                CompletionStage<Conta> maybeUser = PatternsCS
                        .ask(contaActor, new Mensagens.ConsultarDados(), timeout).thenApply(obj -> (Conta) obj);
                return onSuccess(() -> maybeUser, performed -> {
                    return complete(StatusCodes.OK, performed, Jackson.marshaller());
                });
            }
            //#retrieve-user-info
        });
    }

    /*
    private Route deleteUser(String name) {
      return
          //#users-delete-logic
          delete(() -> {
            CompletionStage<ActionPerformed> userDeleted = PatternsCS
              .ask(contaActor, new Mensagens.DeleteUser(name), timeout)
              .thenApply(obj ->(ActionPerformed)obj);
    
            return onSuccess(() -> userDeleted,
              performed -> {
                log.info("Deleted user [{}]: {}", name, performed.getDescription());
                return complete(StatusCodes.OK, performed, Jackson.marshaller());
              }
            );
          });
          //#users-delete-logic
    }
    //#users-get-delete
    */
    //#users-get-post
    private Route getOrPostUsers() {
        return pathEnd(() -> route(post(() -> entity(Jackson.unmarshaller(Conta.class), conta -> {
            ActorRef contaActor = _system.actorOf(ContaActor.props(conta.getIdentificador()), conta.getIdentificador());
            CompletionStage<Conta> userCreated = PatternsCS
                    .ask(contaActor, new Mensagens.AtualizarDados(conta), timeout)
                    .thenApply(obj -> (Conta) obj);
            return onSuccess(() -> userCreated, performed -> {
                log.info("Created user [{}, {}]", performed.getIdentificador(), contaActor.toString());
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        }))));
    }

    //#users-get-post
}
