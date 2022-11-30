package web.service.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotNull
    @NotEmpty
    @JsonProperty(
        value = "username",
        required = true,
        access = JsonProperty.Access.WRITE_ONLY
    )
    public String username;

    @NotNull
    @NotEmpty
    @JsonProperty(
        value = "password",
        access = JsonProperty.Access.WRITE_ONLY
    )
    public String password;

}
