package com.roger.PriceTracker.service;

import com.roger.PriceTracker.model.CoinBaseResponse;
import reactor.core.publisher.Mono;

public interface CoinBaseService {
    Mono<CoinBaseResponse> getPrice(String price);
}
