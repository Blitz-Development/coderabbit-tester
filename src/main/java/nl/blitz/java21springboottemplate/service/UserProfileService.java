package nl.blitz.java21springboottemplate.service;

import nl.blitz.java21springboottemplate.dto.CreateUserProfileDto;
import nl.blitz.java21springboottemplate.dto.UserProfileDto;
import nl.blitz.java21springboottemplate.entity.UserProfile;
import nl.blitz.java21springboottemplate.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;
    private final UserProfileMapper mapper;

    @Autowired
    public UserProfileService(UserProfileRepository repository, UserProfileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserProfileDto> findAll() {
        return repository.findAll().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    public Optional<UserProfileDto> findById(UUID id) {
        return repository.findById(id)
            .map(mapper::toDto);
    }

    public Optional<UserProfileDto> findBySupabaseUserId(UUID supabaseUserId) {
        return repository.findBySupabaseUserId(supabaseUserId)
            .map(mapper::toDto);
    }

    public UserProfileDto create(CreateUserProfileDto dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (dto.getSupabaseUserId() == null) {
            throw new IllegalArgumentException("Supabase user ID cannot be null");
        }
        
        final UserProfile entity = mapper.toEntity(dto);
        final UserProfile saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public Optional<UserProfileDto> update(UUID id, CreateUserProfileDto dto) {
        return repository.findById(id)
            .map(entity -> {
                mapper.updateEntity(entity, dto);
                final UserProfile updated = repository.save(entity);
                return mapper.toDto(updated);
            });
    }

    public boolean delete(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

