package com.gemtrading.gem_service.controller;


import com.gemtrading.gem_service.dto.TagRequest;
import com.gemtrading.gem_service.dto.TagResponse;
import com.gemtrading.gem_service.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagResponse> createTag(
            @Valid @RequestBody TagRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tagService.createTag(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                tagService.getTagById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<TagResponse>> getAllTags(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(
                tagService.getAllTags(page, size)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTag(
            @PathVariable Long id,
            @Valid @RequestBody TagRequest request
    ) {

        return ResponseEntity.ok(
                tagService.updateTag(id, request)
        );
    }
}