package com.gemtrading.gem_service.service;

import com.gemtrading.gem_service.dto.CertificateRequest;
import com.gemtrading.gem_service.dto.CertificateResponse;
import com.gemtrading.gem_service.entity.Certificate;
import com.gemtrading.gem_service.entity.GemStone;
import com.gemtrading.gem_service.exception.DuplicateResourceException;
import com.gemtrading.gem_service.exception.ResourceNotFoundException;
import com.gemtrading.gem_service.repository.CertificateRepository;
import com.gemtrading.gem_service.repository.GemStoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final GemStoneRepo gemStoneRepo;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository , GemStoneRepo gemStoneRepo, GemStoneRepo gemStoneRepo1) {
        this.certificateRepository = certificateRepository;
        this.gemStoneRepo = gemStoneRepo;
    }

    public CertificateResponse createCertificate(CertificateRequest request) {

        if (certificateRepository.existsByCertificateNumber(request.getCertificateNumber())) {
            throw new DuplicateResourceException(
                    "Certificate with number " +
                            request.getCertificateNumber() +
                            " already exists");
        }

        if (certificateRepository.existsByGemStoneId(request.getGemId())) {
            throw new DuplicateResourceException(
                    "Certificate for gem " +
                            request.getGemId() +
                            " already exists");
        }

        GemStone stone = gemStoneRepo.findById(request.getGemId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Gem",
                                request.getGemId().toString()
                        )
                );

        Certificate certificate = Certificate.builder()
                .certificateNumber(request.getCertificateNumber())
                .gemStone(stone)
                .issuedBy(request.getIssuedBy())
                .issuedDate(request.getIssueDate())
                .expiryDate(request.getExpiryDate())
                .remarks(request.getRemarks())
                .build();

        return toResponse(certificateRepository.save(certificate));
    }

    private CertificateResponse toResponse(Certificate cert) {
        return CertificateResponse
                .builder()
                .id(cert.getId())
                .certificateNumber(cert.getCertificateNumber())
                .gemId(cert.getGemStone().getId())
                .gemCode(cert.getGemStone().getGemCode())
                .issuedBy(cert.getIssuedBy())
                .issuedDate(cert.getIssuedDate())
                .expiryDate(cert.getExpiryDate())
                .remarks(cert.getRemarks())
                .createdAt(cert.getCreatedAt())
                .build();
    }

    public CertificateResponse getCertificateById(Long id) throws ResourceNotFoundException {
        return toResponse(certificateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("resource not found" , id.toString())
        ));
    }
}