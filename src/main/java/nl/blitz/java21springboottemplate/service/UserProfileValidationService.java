package nl.blitz.java21springboottemplate.service;

import nl.blitz.java21springboottemplate.dto.CreateUserProfileDto;
import nl.blitz.java21springboottemplate.dto.UpdateUserProfileDto;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserProfileValidationService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    private static final int MAX_DISPLAY_NAME_LENGTH = 100;
    private static final int MIN_DISPLAY_NAME_LENGTH = 1;

    public void validateCreateDto(CreateUserProfileDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("User profile data cannot be null");
        }
        if (dto.getSupabaseUserId() == null) {
            throw new IllegalArgumentException("Supabase user ID cannot be null");
        }
        validateEmail(dto.getEmail());
        validateDisplayName(dto.getDisplayName());
    }

    public void validateUpdateDto(UpdateUserProfileDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("User profile data cannot be null");
        }
        if (dto.getEmail() != null) {
            validateEmail(dto.getEmail());
        }
        if (dto.getDisplayName() != null) {
            validateDisplayName(dto.getDisplayName());
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }
    }

    private void validateDisplayName(String displayName) {
        if (displayName == null || displayName.isBlank()) {
            return;
        }
        final int length = displayName.trim().length();
        if (length < MIN_DISPLAY_NAME_LENGTH || length > MAX_DISPLAY_NAME_LENGTH) {
            throw new IllegalArgumentException(
                String.format("Display name must be between %d and %d characters", 
                    MIN_DISPLAY_NAME_LENGTH, MAX_DISPLAY_NAME_LENGTH)
            );
        }
    }
}

