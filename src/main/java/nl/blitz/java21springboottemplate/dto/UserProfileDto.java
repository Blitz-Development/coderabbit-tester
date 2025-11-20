package nl.blitz.java21springboottemplate.dto;

import java.util.UUID;

public class UserProfileDto {
    private UUID supabaseUserId;
    private String email;
    private String displayName;

    public UserProfileDto() {
    }

    public UserProfileDto(UUID supabaseUserId, String email, String displayName) {
        this.supabaseUserId = supabaseUserId;
        this.email = email;
        this.displayName = displayName;
    }

    public UUID getSupabaseUserId() {
        return supabaseUserId;
    }

    public void setSupabaseUserId(UUID supabaseUserId) {
        this.supabaseUserId = supabaseUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

