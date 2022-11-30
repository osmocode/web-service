package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.models.BikeState;

import java.util.Date;

public class FeedbackProvider {

    @JsonProperty(
            value ="date"
    )
    public Date date;

    @JsonProperty(
            value = "note"
    )
    public int note;

    @JsonProperty(
            value = "comment"
    )
    public String comment;


}
