package com.gemtrading.gem_service.service;


import com.gemtrading.gem_service.dto.GemStoneRequest;
import com.gemtrading.gem_service.dto.GemStoneResponse;
import com.gemtrading.gem_service.entity.GemStone;
import com.gemtrading.gem_service.entity.Tag;
import com.gemtrading.gem_service.exception.ResourceNotFoundException;
import com.gemtrading.gem_service.repository.GemStoneRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GemStoneService {

    private final GemStoneRepo gemStoneRepo;

    private final TagService tagService;

    public Page<GemStoneResponse> getAllGemStones(Pageable pageable) {

//      return gemStoneRepo.findByActiveTrue(pageable).map(item ->toResponse(item));
        return gemStoneRepo.findByActiveTrue(pageable).map(this::toResponse);
    }

    public GemStoneResponse createGemStone(GemStoneRequest request) {

        GemStone gemStone = GemStone.builder()
                .gemCode(request.getGemCode())
                .type(request.getType())
                .color(request.getColor())
                .caratWeight(request.getCaratWeight())
                .origin(request.getOrigin())
                .treatment(request.getTreatment())
                .pricePerCarat(request.getPricePerCarat())
                .stockQuantity(request.getStockQuantity())
                .certified(request.isCertified())
                .description(request.getDescription())
                .active(true)
                .build();

        return toResponse(gemStoneRepo.save(gemStone));
    }

    public GemStoneResponse addTagToGemStone(Long gemId , Long tagId) {
        GemStone gemStone = gemStoneRepo.findById(gemId).orElseThrow(()
                -> new ResourceNotFoundException(gemId.toString(),"resource not found"));

        Tag tag = tagService.getTagEntityById(tagId);
        gemStone.getTags().add(tag);
        return toResponse(gemStoneRepo.save(gemStone));
    }

    public GemStoneResponse getGemStoneById(Long id) throws ResourceNotFoundException {
        return toResponse(gemStoneRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Gem" , id.toString())
        ));
    }

    private GemStoneResponse toResponse(GemStone gemStone) {
        return GemStoneResponse.builder()
                .id(gemStone.getId())
                .gemCode(gemStone.getGemCode())
                .type(gemStone.getType())
                .color(gemStone.getColor())
                .caratWeight(gemStone.getCaratWeight())
                .origin(gemStone.getOrigin())
                .treatment(gemStone.getTreatment())
                .pricePerCarat(gemStone.getPricePerCarat())
                .stockQuantity(gemStone.getStockQuantity())
                .description(gemStone.getDescription())
                .certified(gemStone.isCertified())
                .createdAt(gemStone.getCreatedAt())
                .updatedAt(gemStone.getUpdatedAt()).build();
    }
}