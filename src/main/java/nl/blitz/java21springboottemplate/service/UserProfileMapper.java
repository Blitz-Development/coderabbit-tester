package nl.blitz.java21springboottemplate.service;

import nl.blitz.java21springboottemplate.dto.CreateUserProfileDto;
import nl.blitz.java21springboottemplate.dto.UserProfileDto;
import nl.blitz.java21springboottemplate.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfileDto toDto(UserProfile entity) {
        if (entity == null) {
            return null;
        }
        return new UserProfileDto(
            entity.getSupabaseUserId(),
            entity.getEmail(),
            entity.getDisplayName()
        );
    }

    public UserProfile toEntity(CreateUserProfileDto dto) {
        if (dto == null) {
            return null;
        }
        final UserProfile profile = new UserProfile(dto.getSupabaseUserId(), dto.getEmail());
        profile.setDisplayName(dto.getDisplayName());
        return profile;
    }

    public void updateEntity(UserProfile entity, CreateUserProfileDto dto) {
        if (entity != null && dto != null) {
            entity.setEmail(dto.getEmail());
            entity.setDisplayName(dto.getDisplayName());
        }
    }
}

