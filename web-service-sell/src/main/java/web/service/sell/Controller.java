package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.service.wsdl.sell.*;

import java.rmi.RemoteException;

@RestController
public class Controller {

    @Autowired
    private SellRepository repo;

    @PostMapping("/api/v2/getArticleList")
    public GetArticleListResponse getArticleList(@RequestBody GetArticleListRequest request) throws RemoteException {
        GetArticleListResponse response = new GetArticleListResponse();
        var articles = repo.getArticleListBike(request);
        response.getArticles().addAll(articles);
        return response;
    }

    @PostMapping("/api/v2/getArticle")
    public GetArticleResponse getArticle(@RequestBody GetArticleRequest request) throws RemoteException {
        GetArticleResponse response = new GetArticleResponse();
        var article = repo.getArticleBike(request);
        response.setArticle(article);
        return response;
    }

    @PostMapping("/api/v2/postArticle")
    public PostArticleResponse postArticle(@RequestBody PostArticleRequest request) throws RemoteException {
        PostArticleResponse response = new PostArticleResponse();
        var article = repo.postArticleBike(request);
        response.setArticle(article);
        return response;
    }

    @PostMapping("/api/v2/getBasket")
    public GetBasketResponse getBasket(@RequestBody GetBasketRequest request) throws RemoteException {
        GetBasketResponse response = new GetBasketResponse();
        var basket = repo.getBasket(request);
        response.getArticles().addAll(basket);
        return response;
    }

    @PostMapping("/api/v2/postBasket")
    public PostArticleBasketResponse postBasket(@RequestBody PostArticleBasketRequest request) throws RemoteException {
        PostArticleBasketResponse response = new PostArticleBasketResponse();
        var article = repo.addInBasket(request);
        response.setArticle(article);
        return response;
    }

}
