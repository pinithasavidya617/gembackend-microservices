package com.gemtrading.gem_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateRequest {

    @NotBlank(message = "Certificate number is required")
    private String certificateNumber;

    @NotNull(message = "Gem ID is required")
    private Long gemId;

    @NotBlank(message = "issued by required")
    private String issuedBy;

    @NotNull(message = "Issued date is required")
    private LocalDate issueDate;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    private String remarks;
}