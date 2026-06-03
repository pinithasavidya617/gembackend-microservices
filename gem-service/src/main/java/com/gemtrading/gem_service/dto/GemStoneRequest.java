package com.gemtrading.gem_service.dto;


import com.gemtrading.gem_service.entity.GemOrigin;
import com.gemtrading.gem_service.entity.GemTreatment;
import com.gemtrading.gem_service.entity.GemType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GemStoneRequest {

    @NotBlank(message = "Gem code is required")
    @Size(min = 3 , max = 30 , message = "Gem code must between 3 to 30 characters")
    private String gemCode;

    @NotNull(message = "Gem type is required")
    private GemType type;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull
    @DecimalMin(value = "0.01" , message = "Value must be greater than 0.01")
    private BigDecimal caratWeight;

    @NotNull(message = "Origin is required")
    private GemOrigin origin;

    @NotNull(message = "Gen treatment is required")
    private GemTreatment treatment;

    @NotNull
    @DecimalMin(value = "0.01" , message = "Value must be greater than 0.01" )
    private BigDecimal pricePerCarat;

    @NotNull
    @Min(value = 0 , message = "Value has to be greater than 0")
    private Integer stockQuantity;

    @NotBlank
    @Size(max = 1000)
    private String description;

    private boolean certified;
}