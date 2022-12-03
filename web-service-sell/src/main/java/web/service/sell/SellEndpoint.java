package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import web.service.wsdl.sell.*;

@Endpoint
public class SellEndpoint {

    private static final String NAMESPACE = "http://www.springframework.org/schema/web-services";

    @Autowired
    private SellRepository repo;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getArticleRequest")
    @ResponsePayload
    public GetArticleResponse getArticleResponse(@RequestPayload GetArticleRequest request) {
        GetArticleResponse response = new GetArticleResponse();
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE, localPart = "getArticleListRequest")
    @ResponsePayload
    public GetArticleListResponse getArticleListResponse(@RequestPayload GetArticleListRequest request) {
        GetArticleListResponse response = new GetArticleListResponse();
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "postArticleRequest")
    @ResponsePayload
    public PostArticleResponse postArticleResponse(@RequestPayload PostArticleRequest request) {
        PostArticleResponse response = new PostArticleResponse();
        return response;
    }


}
