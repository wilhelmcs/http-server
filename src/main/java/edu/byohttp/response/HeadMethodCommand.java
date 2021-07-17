package edu.byohttp.response;


import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;
import edu.byohttp.resource.ResourceManagerImpl;

import java.util.HashMap;
import java.util.Optional;



public class HeadMethodCommand implements  Command{
    private final ResourceManagerImpl resourceManager;
    private final ResponseFactory responseFactory;
    private final NotFoundResponseHandler notFoundResponseHandler;
    private static final String HTTP_NOT_FOUND_RESOURCE_PATH = "404NotFound.html";

    public HeadMethodCommand(ResourceManagerImpl resourceManager,ResponseFactory responseFactory, NotFoundResponseHandler notFoundResponseHandler){
        this.resourceManager = resourceManager;
        this.responseFactory = responseFactory;
        this.notFoundResponseHandler = notFoundResponseHandler;
    }

    @Override
    public Response execute(Request request) {
        Optional<Resource> searchedResource = resourceManager.searchResource(request.getResource());
        if (searchedResource.isEmpty()){
            Resource notFoundResource = resourceManager.searchResource(HTTP_NOT_FOUND_RESOURCE_PATH).get();
            Response response = notFoundResponseHandler.handle(request, notFoundResource);
            return response;
        }
        else {
            Resource foundResource = searchedResource.get();
            String contentLength = String.valueOf(foundResource.getContentLength());
            HashMap<String,String> headersResponseMap= new HashMap<>();
            java.util.Date date=new java.util.Date();
            headersResponseMap.put("Server","ByoHttp/0.0.1");
            headersResponseMap.put("Date", date.toString());
            headersResponseMap.put("Content-Type", foundResource.getContentType());
            headersResponseMap.put("Content-Length", contentLength);
            headersResponseMap.put("Last-Modified", foundResource.getLastModifiedTime());
            headersResponseMap.put("Connection", request.getHeaders("Connection:"));
            headersResponseMap.put("Accept-Ranges", "bytes");

            return responseFactory.getResponse(HttpResponseStatusCode.OK,request.getProtocolVersion(),headersResponseMap, Optional.empty() );
        }
    }
}
