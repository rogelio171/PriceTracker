package com.roger.PriceTracker.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.roger.PriceTracker.service.CoinBaseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceRequestor extends AbstractActor {
    private final ActorRef printerActor;
    private final CoinBaseService coinBaseService;

    public static Props props(ActorRef printerActor, CoinBaseService coinBaseService) {
        return Props.create(PriceRequestor.class, () -> new PriceRequestor(printerActor, coinBaseService));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(
                GetPrice.class,
                what -> printerActor
                        .tell(new Printer.Price(coinBaseService.getPrice(what.whatPrice)), getSelf())
        ).build();
    }

    @AllArgsConstructor
    public static class GetPrice {
        public final String whatPrice;
    }
}
