package web.service.rest.providers;

import java.rmi.RemoteException;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import rmi.bike.interfaces.feedback.FeedbackService;

public class FeedbackProvider {

    @JsonProperty(
        value = "id",
        access = JsonProperty.Access.READ_ONLY
    )
    public String uuid;

    @JsonProperty(
        value = "date",
        access = JsonProperty.Access.READ_WRITE
    )
    public String date;

    @JsonProperty(
        value = "note",
        access = JsonProperty.Access.READ_WRITE
    )
    public int note;
    
    @JsonProperty(
        value = "comment",
        access = JsonProperty.Access.READ_WRITE
    )
    public String comment;

    @JsonProperty(
        value = "bike_state",
        access = JsonProperty.Access.READ_WRITE
    )
    public String bikeState;

    public FeedbackProvider(){}
    
    public FeedbackProvider(UUID uuid, FeedbackService feedbackInterface) throws RemoteException {
        this.uuid = uuid.toString();
        this.date = feedbackInterface.getDate().toString();
        this.comment = feedbackInterface.getComment();
        this.note = feedbackInterface.getNote();
        this.bikeState = feedbackInterface.getBikeState().toString();
    }
}
