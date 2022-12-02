package web.service.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.customer.interfaces.CustomerListService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@ComponentScan
@Configuration
public class Config {

    @Bean
    BikeListService getBikeService(@Value("#{environment.BIKE_SERVICE_HOST}") String bikeServiceHost) throws RemoteException, MalformedURLException, NotBoundException {
        return (BikeListService) Naming.lookup("rmi://"+bikeServiceHost);
    }

    @Bean
    CustomerListService getCustomerService(@Value("#{environment.CUSTOMER_SERVICE_HOST}") String customerServiceHost) throws  RemoteException, MalformedURLException, NotBoundException {
        return (CustomerListService) Naming.lookup("rmi://"+customerServiceHost);
    }

    @Bean
    FeedbackListService getFeedbackService(@Value("#{environment.FEEDBACK_SERVICE_HOST}") String feedbackServiceHost) throws  RemoteException, MalformedURLException, NotBoundException {
        return (FeedbackListService) Naming.lookup("rmi://"+feedbackServiceHost);
    }

}
