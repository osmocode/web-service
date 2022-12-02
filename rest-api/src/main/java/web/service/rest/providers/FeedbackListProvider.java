package web.service.rest.providers;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import rmi.bike.interfaces.feedback.FeedbackService;

public class FeedbackListProvider {
    @JsonProperty(
        value = "count",
        access = JsonProperty.Access.READ_ONLY
    )
    public int count;

    @JsonProperty(
        value = "results",
        access = JsonProperty.Access.READ_ONLY
    )
    public List<FeedbackProvider> feedbacks;

    public FeedbackListProvider(Map<UUID, ? extends FeedbackService> feedbacks) {
        this.count = feedbacks.size();
        this.feedbacks = feedbacks.entrySet().stream().map((entry) -> {
            try {
                return new FeedbackProvider(entry.getKey(), entry.getValue());
            } catch (RemoteException e) {
                throw new RuntimeException(e.getCause());
            }
        }).toList();
    }
}
