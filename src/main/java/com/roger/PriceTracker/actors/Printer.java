package com.roger.PriceTracker.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import com.roger.PriceTracker.model.CoinBaseResponse;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Printer extends AbstractActor {

    public static Props props() {
        return Props.create(Printer.class, Printer::new);
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Price.class,
                res -> res.message.subscribe(coinBaseResponse -> System.out.println("[" + LocalDateTime.now() + "] "
                        + coinBaseResponse.getData().getBase()
                        + " Buy Price: $" + coinBaseResponse.getData().getAmount()
                        + " " + coinBaseResponse.getData().getCurrency()))).build();
    }

    @AllArgsConstructor
    public static class Price {
        public final Mono<CoinBaseResponse> message;
    }
}
