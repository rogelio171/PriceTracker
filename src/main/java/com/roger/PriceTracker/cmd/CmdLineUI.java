package com.roger.PriceTracker.cmd;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.roger.PriceTracker.actors.Poller;
import com.roger.PriceTracker.actors.PriceRequestor;
import com.roger.PriceTracker.actors.Printer;
import com.roger.PriceTracker.service.CoinBaseService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CmdLineUI implements CommandLineRunner {
    private final CoinBaseService coinBaseService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\n...::: Coinbase Price Tracker :::...\n\n");

        final ActorSystem system = ActorSystem.create("AkkaPriceTracker");
        final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");
        final ActorRef priceRequestor = system.actorOf(
                PriceRequestor.props(printerActor, coinBaseService), "priceRequestor");
        final ActorRef poller = system.actorOf(
                Poller.props("BTC-USD", priceRequestor), "poller");

        final ActorRef ethPoller = system.actorOf(
                Poller.props("ETH-USD", priceRequestor), "ethPoller");
    }
}
