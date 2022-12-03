package web.service.bank;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BankService {

    private final HashMap<String, HashMap<String, Double>> bank = new HashMap<>();



}
