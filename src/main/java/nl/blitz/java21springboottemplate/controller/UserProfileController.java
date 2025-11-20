package nl.blitz.java21springboottemplate.controller;

import nl.blitz.java21springboottemplate.dto.CreateUserProfileDto;
import nl.blitz.java21springboottemplate.dto.UserProfileDto;
import nl.blitz.java21springboottemplate.entity.UserProfile;
import nl.blitz.java21springboottemplate.repository.UserProfileRepository;
import nl.blitz.java21springboottemplate.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @Autowired
    private UserProfileRepository repository;

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

    @GetMapping("/supabase/{supabaseUserId}")
    public ResponseEntity<UserProfileDto> getBySupabaseUserId(@PathVariable UUID supabaseUserId) {
        final Optional<UserProfileDto> profile = service.findBySupabaseUserId(supabaseUserId);
        return profile.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> create(@RequestBody CreateUserProfileDto dto) {
        try {
            final UserProfileDto created = service.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDto> update(@PathVariable UUID id, @RequestBody CreateUserProfileDto dto) {
        final Optional<UserProfileDto> updated = service.update(id, dto);
        return updated.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        final boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/direct/{id}")
    public ResponseEntity<UserProfile> getDirectly(@PathVariable UUID id) {
        final Optional<UserProfile> profile = repository.findById(id);
        return profile.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}

