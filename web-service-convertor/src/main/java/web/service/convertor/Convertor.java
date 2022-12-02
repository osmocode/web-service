package web.service.convertor;

import java.util.*;

public class Convertor {

    record Currency(String from, String to, Double rate) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Currency currency = (Currency) o;
            return from.equals(currency.from) && to.equals(currency.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    private final HashMap<Integer, Currency> map = new HashMap();

    public void addCurrency(Currency currency) {
        this.map.put(currency.hashCode(), currency);
    }

    public Double getCurrency(String from, String to, Double amount) {
        var request = new Currency(from, to, 0.0);
        var currency = this.map.getOrDefault(
                request.hashCode(),
                new Currency("NULL", "NULL", 1.0)
        );
        return amount * currency.rate;
    }

    public List<Currency> getCurrencyList() {
        return this.map.values().stream().toList();
    }



}
