package web.service.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TokenForm {

    @JsonProperty(
        value = "token",
        access = JsonProperty.Access.READ_ONLY
    )
    public String token;

    public TokenForm(UUID token) {
        this.token = token.toString();
    }

}
