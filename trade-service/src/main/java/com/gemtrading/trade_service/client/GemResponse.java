package com.gemtrading.trade_service.client;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GemResponse {
    private Long id;
    private String gemCode;
    private String type;
    private String color;
    private BigDecimal caratWeight;
    private String origin;
    private String treatment;
    private BigDecimal pricePerCarat;
    private Integer stockQuantity;
    private Boolean certified;
    private Boolean active;
    private String description;
}
