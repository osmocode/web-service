package web.service.bank;

import org.springframework.stereotype.Component;
import web.service.wsdl.bank.Wallet;

import java.util.HashMap;
import java.util.List;

@Component
public class BankService {

    private final HashMap<String, HashMap<String, Double>> bank = new HashMap<>();

    public List<Wallet> getWalletList(String id) {
        var wallet = this.bank.getOrDefault(id, new HashMap<>());
        return wallet.entrySet().stream().map((entry) -> {
            var w = new Wallet();
            w.setCurrency(entry.getKey());
            w.setBalance(entry.getValue());
            return w;
        }).toList();
    }

    public Wallet getWallet(String id, String currency) {
        var wallet = this.bank.getOrDefault(id, new HashMap<>());
        var value = wallet.getOrDefault(currency, 0.0);
        var response = new Wallet();
        response.setCurrency(currency);
        response.setBalance(value);
        return response;
    }

    public Wallet addWallet(String id, String currency, Double sum) {
        var wallet = this.bank.getOrDefault(id, new HashMap<>());
        var value = wallet.getOrDefault(currency, 0.0);
        value += sum;
        wallet.put(currency, value);
        this.bank.put(id, wallet);
        var response = new Wallet();
        response.setCurrency(currency);
        response.setBalance(value);
        return response;
    }

    public Wallet removeWallet(String id, String currency, Double sum) {
        var wallet = this.bank.getOrDefault(id, new HashMap<>());
        var value = wallet.getOrDefault(currency, 0.0);
        value -= sum;
        wallet.put(currency, value);
        this.bank.put(id, wallet);
        var response = new Wallet();
        response.setCurrency(currency);
        response.setBalance(value);
        return response;
    }

}
