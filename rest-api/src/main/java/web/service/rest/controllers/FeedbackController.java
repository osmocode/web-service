package web.service.rest.controllers;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.models.BikeState;
import web.service.rest.providers.FeedbackListProvider;
import web.service.rest.providers.FeedbackProvider;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackListService service;


    @GetMapping("/feedback")
    public FeedbackListProvider getFeedbackAll() throws RemoteException {
        return new FeedbackListProvider(service.getAll());
    }

    @GetMapping("/feedback/{id}")
    public FeedbackProvider getFeedbackById(String id) throws RemoteException {
        var feedback = service.getFeedbackByUUID(id);
        if (feedback == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "feedback not found");
        }
        return new FeedbackProvider(UUID.fromString(id), feedback);
    }

    @PostMapping("/feedback")
    public FeedbackProvider putCustomer(@Valid @RequestBody FeedbackProvider feedback) throws RemoteException {
        var entry = service.add(Date.valueOf(feedback.date), feedback.note, feedback.comment,BikeState.valueOf(feedback.bikeState.toUpperCase()), UUID.fromString(feedback.uuid));
        if(entry == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "feedback not created");
        }
        var response =  entry.entrySet().stream().findFirst();
        if(response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "feedback creation error");
        }
        return new FeedbackProvider(response.get().getKey(), response.get().getValue());
    }
}
