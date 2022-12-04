package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.interfaces.rent.RentListService;
import rmi.bike.models.BikeState;
import rmi.customer.interfaces.CustomerListService;
import web.service.rest.providers.FeedbackProvider;
import web.service.rest.session.Authenticated;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.UUID;

@RestController
public class FeedbackController {

    @Autowired
    RentListService rentService;

    @Autowired
    FeedbackListService feedbackService;

    @Autowired
    CustomerListService customerService;

    @Authenticated
    @GetMapping("/api/v1/feedback/{id}")
    public FeedbackProvider getFeedback(
            @RequestHeader(value = "X-Auth-Token") String token,
            @PathVariable("id") String uuid) throws RemoteException {
        var feedback = feedbackService.getFeedbackByUUID(uuid);
        if (feedback == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found");
        }
        return new FeedbackProvider(UUID.fromString(uuid), feedback);
    }

    @GetMapping("/api/v1/feedback/stats")
    public FeedbackStats getFeedbackStats(
            @RequestHeader(value = "X-Auth-Token") String token) throws RemoteException {
        return new FeedbackStats();
    }

    @Authenticated
    @PostMapping("/api/v1/feedback/{id}")
    public FeedbackProvider postFeedback(
            @RequestHeader(value = "X-Auth-Token") String token,
            @PathVariable("id") String uuid,
            @Valid @RequestBody FeedbackProvider feedback) throws RemoteException {
        var user = customerService.isLogged(UUID.fromString(token));
        var rental = rentService.getRentByUUID(uuid);
        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found");
        }
        var feed = feedbackService.add(
                new Date(),
                feedback.note,
                feedback.comment,
                BikeState.valueOf(feedback.state),
                UUID.fromString(uuid));
        if (feed == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Feedback creation error");
        }
        return new FeedbackProvider(UUID.fromString(uuid), feed);
    }

    class FeedbackStats{
        @JsonProperty(
        value = "num_feedback",
        access = JsonProperty.Access.READ_ONLY
    )
    public final long totalFeedbacks ;
    public FeedbackStats() throws RemoteException{
        totalFeedbacks = feedbackService.getAll().size();
    }
    }

}
