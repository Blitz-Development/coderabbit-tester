package nl.blitz.java21springboottemplate.dto;

public class UpdateUserProfileDto {
    private String email;
    private String displayName;

    public UpdateUserProfileDto() {
    }

    public UpdateUserProfileDto(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
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

