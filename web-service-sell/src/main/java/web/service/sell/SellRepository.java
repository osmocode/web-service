package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.interfaces.bike.BikeService;
import rmi.customer.interfaces.CustomerListService;
import web.service.wsdl.sell.*;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class SellRepository {


    @Autowired
    private BikeListService bikeService;

    @Autowired
    private CustomerListService authService;


    private final HashMap<String, SellPrice> sell = new HashMap<>();
    private final HashMap<String, List<String>> baskets = new HashMap<>();
    private final List<String> sold = new ArrayList<>();

    private boolean currencyIsValid(String currency) {
        Objects.requireNonNull(currency);

        return !currency.isEmpty() && !currency.isBlank();
    }

    private boolean tokenIsValid(String token) throws RemoteException {
        return authService.isLogged(UUID.fromString(Objects.requireNonNull(token))) != null;
    }

    private boolean uuidBikeIsValid(String uuid) {
        return sell.containsKey(Objects.requireNonNull(uuid));
    }

    private boolean uuidCustomerIsValid(String uuid) {
        return baskets.containsKey(Objects.requireNonNull(uuid));
    }

    /////////// SELL ////////////
    private BikeService getBike(String uuid) throws RemoteException {
        return bikeService.getBikeByUUID(Objects.requireNonNull(uuid));
    }

    private Article bikeIntoArticle(String uuid, String currency) throws RemoteException {
        Objects.requireNonNull(uuid);
        Objects.requireNonNull(currency);

        var price = sell.get(uuid);
        if (price == null) { return null; }

        var bike = getBike(uuid);
        if (bike == null) { return null; }

        var article = new Article();

        article.setUuid(uuid);
        article.setLabel(bike.getLabel());
        article.setDescription(bike.getDescription());
        article.setOwner(bike.getOwnerId().toString());
        article.setState(bike.getBikeState().toString());
        article.setPrice(price.value()); // TODO convert into currency
        article.setCurrency(currency);
        article.setAvailable(bike.getRentQueue().isEmpty());

        return article;
    }

    public Article getArticleBike(GetArticleRequest request) throws RemoteException {
        Objects.requireNonNull(request);

        if (!tokenIsValid(request.getToken())) {
            return null;
        }

        return bikeIntoArticle(request.getUuid(), request.getCurrency());
    }

    public List<Article> getArticleListBike(GetArticleListRequest request) throws RemoteException {
        Objects.requireNonNull(request);

        if (!tokenIsValid(request.getToken())) { return null; }

        return sell.entrySet().stream().map(stringSellPriceEntry -> {
            try {
                return bikeIntoArticle(stringSellPriceEntry.getKey(), request.getCurrency());
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new IllegalStateException(); // TODO other exception ?
            }
        }).collect(Collectors.toList());
    }

    public Article postArticleBike(PostArticleRequest request) throws RemoteException {
        Objects.requireNonNull(request);

        if (!tokenIsValid(request.getToken())) { return null; }

        if (sell.containsKey(request.getUuid())) {
            return null;
        }

        sell.put(request.getUuid(), new SellPrice(request.getCurrency(), request.getPrice()));

        return bikeIntoArticle(request.getUuid(), request.getCurrency());
    }
    ////////////////////////////

    ///////// BASKET ///////////
    private boolean isSold(String uuid) {
        return sold.contains(Objects.requireNonNull(uuid));
    }

    private void filterBasket(String uuid) {
        Objects.requireNonNull(uuid);

        if (baskets.get(uuid) == null) {
            return ;
        }

        baskets.get(uuid).removeAll(baskets.get(uuid).stream().filter(s -> isSold(s)).toList());
    }

    public List<Article> getBasket(GetBasketRequest request) throws RemoteException {
        Objects.requireNonNull(request);

        if (!tokenIsValid(request.getToken())) { return null; }

        var uuid = request.getUuid();

        filterBasket(uuid);

        if (baskets.get(uuid) == null) {
            return new ArrayList<>();
        }

        return baskets.get(uuid).stream().map(article -> {
            try {
                return bikeIntoArticle(article, request.getCurrency());
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new IllegalStateException(); // TODO other exception ?
            }
        }).collect(Collectors.toList());
    }

    public Article addInBasket(PostArticleBasketRequest request) throws RemoteException {
        Objects.requireNonNull(request);

        if (!tokenIsValid(request.getToken())) { return null; }

        var customerUuid = authService.isLogged(UUID.fromString(request.getToken())).toString();
        var bikeUuid = request.getBikeUuid();

        baskets.putIfAbsent(customerUuid, new ArrayList<>());
        baskets.get(customerUuid).add(bikeUuid);

        filterBasket(customerUuid);

        var article = bikeIntoArticle(bikeUuid, request.getCurrency());

        if (article.getOwner().equals(authService.isLogged(UUID.fromString(request.getToken())).toString())) {
            return null;
        }

        return article;
    }

    public List<Article> buyBasket(PostBuyBasketRequest request) throws RemoteException {
        Objects.requireNonNull(request);

        if (!tokenIsValid(request.getToken())) { return null; }

        var customerUuid = authService.isLogged(UUID.fromString(Objects.requireNonNull(request.getToken()))).toString();

        filterBasket(customerUuid);

        return baskets.get(customerUuid).stream().map(article -> {
            try {
                var bike = bikeIntoArticle(article, request.getCurrency());

                // TODO buy bike

                return bike;
            } catch (RemoteException e) {
                e.printStackTrace();
                throw new IllegalStateException(); // TODO other exception ?
            }
        }).collect(Collectors.toList());
    }
    ////////////////////////////
}
