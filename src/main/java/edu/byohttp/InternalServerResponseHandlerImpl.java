package edu.byohttp;

import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;
import edu.byohttp.resource.ResourceManagerImpl;
import edu.byohttp.response.HttpResponseStatusCode;
import edu.byohttp.response.Response;
import edu.byohttp.response.ResponseFactory;

import java.util.HashMap;
import java.util.Optional;

public class InternalServerResponseHandlerImpl implements InternalServerErrorHandler{

    private final ResourceManagerImpl resourceManager;
    private final ResponseFactory responseFactory;
    private static final String HTTP_INTERNAL_SERVER_ERROR_PATH = "500InternalServer.html";

    public InternalServerResponseHandlerImpl(ResourceManagerImpl resourceManager, ResponseFactory responseFactory){
        this.resourceManager = resourceManager;
        this.responseFactory = responseFactory;
    }

    @Override
    public Response handle(Request request) {
        Resource internalServerErrorHTML = resourceManager.searchResource(HTTP_INTERNAL_SERVER_ERROR_PATH).get();
        String contentLength = String.valueOf(internalServerErrorHTML.getContentLength());
        HashMap<String,String> headersErrorResponseMap = new HashMap<>();
        java.util.Date date = new java.util.Date();
        headersErrorResponseMap.put("Server","ByoHttp/0.0.1");
        headersErrorResponseMap.put("Date", date.toString());
        headersErrorResponseMap.put("Content-Type", internalServerErrorHTML.getContentType());
        headersErrorResponseMap.put("Content-Length", contentLength);
        headersErrorResponseMap.put("Connection", "closed");
        return responseFactory.getResponse(HttpResponseStatusCode.INTERNAL_SERVER_ERROR, request.getProtocolVersion(),headersErrorResponseMap, Optional.of(internalServerErrorHTML.getResourceBytes()));
    }
}
