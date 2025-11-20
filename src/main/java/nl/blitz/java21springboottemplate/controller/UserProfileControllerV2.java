package nl.blitz.java21springboottemplate.controller;

import nl.blitz.java21springboottemplate.dto.CreateUserProfileDto;
import nl.blitz.java21springboottemplate.dto.UpdateUserProfileDto;
import nl.blitz.java21springboottemplate.dto.UserProfileDto;
import nl.blitz.java21springboottemplate.service.UserProfileService;
import nl.blitz.java21springboottemplate.service.UserProfileValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/user-profiles")
public class UserProfileControllerV2 {

    private final UserProfileService service;
    private final UserProfileValidationService validationService;

    @Autowired
    public UserProfileControllerV2(UserProfileService service, UserProfileValidationService validationService) {
        this.service = service;
        this.validationService = validationService;
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getAll() {
        final List<UserProfileDto> profiles = service.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getById(@PathVariable UUID id) {
        final Optional<UserProfileDto> profile = service.findById(id);
        return profile.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> create(@RequestBody CreateUserProfileDto dto) {
        validationService.validateCreateDto(dto);
        final UserProfileDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDto> update(@PathVariable UUID id, @RequestBody UpdateUserProfileDto dto) {
        validationService.validateUpdateDto(dto);
        final Optional<UserProfileDto> updated = service.update(id, dto);
        return updated.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        final boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

