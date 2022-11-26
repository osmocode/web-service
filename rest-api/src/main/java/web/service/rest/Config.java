package web.service.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@ComponentScan
@Configuration
public class Config {

    @Bean
    String getBikeService(@Value("#{environment.BIKE_SERVICE_HOST}") String bikeServiceHost) throws RemoteException, MalformedURLException, NotBoundException {
        Naming.lookup("rmi://"+bikeServiceHost);
        return bikeServiceHost;
    }



    //ILibrary bib = (ILibrary) Naming.lookup("rmi://localhost:1099/BibService");

}
