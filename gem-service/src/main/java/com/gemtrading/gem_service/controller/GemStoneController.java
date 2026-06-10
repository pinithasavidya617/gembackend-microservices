package com.gemtrading.gem_service.controller;

import com.gemtrading.gem_service.dto.GemStoneRequest;
import com.gemtrading.gem_service.dto.GemStoneResponse;
import com.gemtrading.gem_service.service.GemStoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gems")
@RequiredArgsConstructor
public class GemStoneController {

    private final GemStoneService gemStoneService;

    @GetMapping
    public ResponseEntity<Page<GemStoneResponse>> getAllGems(
            @PageableDefault(size = 20, sort = "color") Pageable pageable
    ) {//Spring automatically creates Pageable object from query params.

        return ResponseEntity.ok( //HTTP 200 OK
                gemStoneService.getAllGemStones(pageable)
        );
    }

    @PostMapping
    public ResponseEntity<GemStoneResponse> createGemStone(
           @Valid @RequestBody GemStoneRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Custom-Header", "Sending custom header")
                .body(gemStoneService.createGemStone(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GemStoneResponse> getGemStoneById(
            @PathVariable Long id
    ) {
        System.out.println("I got a hit");
        return ResponseEntity.ok(
                gemStoneService.getGemStoneById(id)
        );
    }
}