package com.roger.PriceTracker.cmd;

import com.roger.PriceTracker.observer.ConsolePrintObserver;
import com.roger.PriceTracker.service.CoinBaseService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CmdLineUI implements CommandLineRunner {
    private final CoinBaseService coinBaseService;

    public CmdLineUI(CoinBaseService coinBaseService) {
        this.coinBaseService = coinBaseService;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nCrypto Price: ");

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tick ->coinBaseService.getPrice("BTC-USD"))
                .subscribe(new ConsolePrintObserver());

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tick ->coinBaseService.getPrice("ETH-USD"))
                .subscribe(new ConsolePrintObserver());

    }
}
