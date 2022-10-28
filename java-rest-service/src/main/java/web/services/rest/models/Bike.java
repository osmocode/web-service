package web.services.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bike {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
