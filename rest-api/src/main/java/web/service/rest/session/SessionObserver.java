package web.service.rest.session;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import rmi.customer.interfaces.CustomerListService;

import java.util.UUID;

@Aspect
@Component
public class SessionObserver {

    @Autowired
    private CustomerListService service;

    @Before(value = "@annotation(web.service.rest.session.Authenticated) && args(token,..)")
    public void isAuthenticated(JoinPoint joinPoint, String token) throws Throwable {
        if (token == null || token.isEmpty() || token.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token not found");
        }
        var user = service.isLogged(UUID.fromString(token));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
        }
    }

}
