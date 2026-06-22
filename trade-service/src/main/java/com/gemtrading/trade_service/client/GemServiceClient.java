package com.gemtrading.trade_service.client;

import com.gemtrading.trade_service.exception.GemServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class GemServiceClient {

    private final RestClient restClient;
    private static final Logger log = LoggerFactory.getLogger(GemServiceClient.class);

    private static final String GEM_SERVICE_URL = "http://GEM-TRADING";

    @CircuitBreaker(
            name = "gemService",
            fallbackMethod = "getGemFallBack"
    )
    public GemResponse getGem(Long gemId) {

        return restClient.get()
                .uri(GEM_SERVICE_URL + "/api/v1/gems/{id}", gemId)
                .retrieve()
                .body(GemResponse.class);
    }

    public GemResponse getGemFallBack(Long gemId, Throwable throwable) {
        log.warn("Circuit breaker fallback for getGem ({}), Reason: ({})", gemId, throwable.getMessage());

        return GemResponse.builder()
                .id(gemId)
                .gemCode("UNKNOWN")
                .description("SERVICE UNAVAILABLE")
                .pricePerCarat(BigDecimal.ZERO)
                .build();
    }
}