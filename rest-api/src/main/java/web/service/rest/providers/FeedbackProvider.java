package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.feedback.FeedbackService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.rmi.RemoteException;
import java.util.UUID;

public class FeedbackProvider {

    @JsonProperty(
        value = "id",
        access = JsonProperty.Access.READ_ONLY
    )
    public String uuid;

    @JsonProperty(
        value = "date",
        access = JsonProperty.Access.READ_ONLY
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
    public String state;

    public FeedbackProvider() {}

    public FeedbackProvider(UUID uuid, FeedbackService feedbackService) throws RemoteException {
        this.uuid = uuid.toString();
        this.date = feedbackService.getDate().toString();
        this.note = feedbackService.getNote();
        this.comment = feedbackService.getComment();
        this.state = feedbackService.getBikeState().toString();
    }


}
