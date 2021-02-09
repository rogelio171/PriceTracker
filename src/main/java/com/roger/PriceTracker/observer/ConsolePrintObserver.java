package com.roger.PriceTracker.observer;

import com.roger.PriceTracker.model.CoinBaseResponse;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DefaultObserver;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ConsolePrintObserver<T> extends DefaultObserver<T> {
    @Override
    public void onNext(@NonNull T o) {
        Mono<CoinBaseResponse> responseMono = (Mono<CoinBaseResponse>) o;

        responseMono.subscribe(coinBaseResponse -> System.out.println("[" + LocalDateTime.now() + "] "
                + coinBaseResponse.getData().getBase()
                + " Buy Price: $" + coinBaseResponse.getData().getAmount()
                + " " + coinBaseResponse.getData().getCurrency()));
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println("Error: " + e);
    }

    @Override
    public void onComplete() {
        System.out.println("Complete");
    }
}
