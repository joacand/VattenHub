package se.joacand.vattenhub.domain.hue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkSuccess {

    @JsonProperty
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}