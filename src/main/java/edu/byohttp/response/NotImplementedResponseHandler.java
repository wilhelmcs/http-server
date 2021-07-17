package edu.byohttp.response;

import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class NotImplementedResponseHandler implements ErrorResponseHandler{
    private ResponseFactory responseFactory;
    public NotImplementedResponseHandler(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Response handle(Request request, Resource resource){
        String contentLength = String.valueOf(resource.getContentLength());
        HashMap<String,String> headersErrorResponseMap = new HashMap<>();
        Date date = new Date();
        headersErrorResponseMap.put("Server","ByoHttp/0.0.1");
        headersErrorResponseMap.put("Date", date.toString());
        headersErrorResponseMap.put("Content-Type", resource.getContentType());
        headersErrorResponseMap.put("Content-Length", contentLength);
        headersErrorResponseMap.put("Connection", request.getHeaders("Connection:"));
        return responseFactory.getResponse(HttpResponseStatusCode.NOT_IMPLEMENTED_ERROR,request.getProtocolVersion(),headersErrorResponseMap, Optional.of(resource.getResourceBytes()));
    }
}
