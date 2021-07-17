package edu.byohttp.response;

import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;

import java.util.HashMap;
import java.util.Optional;

public class NotFoundResponseHandler implements ErrorResponseHandler{
    private ResponseFactory responseFactory;
    public NotFoundResponseHandler(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Response handle(Request request, Resource resource){
        String contentLength = String.valueOf(resource.getContentLength());
        HashMap<String,String> headersErrorResponseMap = new HashMap<>();
        java.util.Date date = new java.util.Date();
        headersErrorResponseMap.put("Server","ByoHttp/0.0.1");
        headersErrorResponseMap.put("Date", date.toString());
        headersErrorResponseMap.put("Content-Type", resource.getContentType());
        headersErrorResponseMap.put("Content-Length", contentLength);
        headersErrorResponseMap.put("Connection", request.getHeaders("Connection:"));
        return responseFactory.getResponse(HttpResponseStatusCode.NOT_FOUND,request.getProtocolVersion(),headersErrorResponseMap, Optional.of(resource.getResourceBytes()));
    }
}
