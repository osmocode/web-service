package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import web.service.wsdl.sell.*;

import java.rmi.RemoteException;

@Endpoint
public class SellEndpoint {

    private static final String NAMESPACE = "http://www.springframework.org/schema/web-services";

    @Autowired
    private SellRepository repo;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getArticleRequest")
    @ResponsePayload
    public GetArticleResponse getArticleResponse(@RequestPayload GetArticleRequest request) {
        GetArticleResponse response = new GetArticleResponse();

        try {
            var article = repo.getArticleBike(request);
            response.setArticle(article);
        } catch (RemoteException e) {
            e.printStackTrace(); // TODO Exception ?
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getArticleListRequest")
    @ResponsePayload
    public GetArticleListResponse getArticleListResponse(@RequestPayload GetArticleListRequest request) {
        GetArticleListResponse response = new GetArticleListResponse();

        try {
            var articles = repo.getArticleListBike(request);
            response.getArticles().addAll(articles);
        } catch (RemoteException e) {
            e.printStackTrace(); // TODO Exception ?
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "postArticleRequest")
    @ResponsePayload
    public PostArticleResponse postArticleResponse(@RequestPayload PostArticleRequest request) {
        PostArticleResponse response = new PostArticleResponse();

        try {
            var article = repo.postArticleBike(request);
            response.setArticle(article);
        } catch (RemoteException e) {
            e.printStackTrace(); // TODO Exception ?
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBasketRequest")
    @ResponsePayload
    public GetBasketResponse getBasketResponse(@RequestPayload GetBasketRequest request) {
        GetBasketResponse response = new GetBasketResponse();

        try {
            var basket = repo.getBasket(request);
            response.getArticles().addAll(basket);
        } catch (RemoteException e) {
            e.printStackTrace(); // TODO Exception ?
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "postArticleBasketRequest")
    @ResponsePayload
    public PostArticleBasketResponse postArticleBasketResponse(@RequestPayload PostArticleBasketRequest request) {
        PostArticleBasketResponse response = new PostArticleBasketResponse();

        try {
            var article = repo.addInBasket(request);
            response.setArticle(article);
        } catch (RemoteException e) {
            e.printStackTrace(); // TODO Exception ?
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "postBuyBasketRequest")
    @ResponsePayload
    public PostBuyBasketResponse postBuyBasketResponse(@RequestPayload PostBuyBasketRequest request) {
        PostBuyBasketResponse response = new PostBuyBasketResponse();

        try {
            var basket = repo.buyBasket(request);
            response.getArticles().addAll(basket);
        } catch (RemoteException e) {
            e.printStackTrace(); // TODO Exception ?
        }

        return response;
    }
}
