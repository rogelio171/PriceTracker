package com.roger.PriceTracker.service;

import com.roger.PriceTracker.model.CoinBaseResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CoinBaseServiceImpl implements CoinBaseService {
    private final WebClient webClient;

    public CoinBaseServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<CoinBaseResponse> getPrice(String price) {
        return webClient
                .get()
                .uri("https://api.coinbase.com/v2/prices/{currencyName}/buy", price)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
    }
}
