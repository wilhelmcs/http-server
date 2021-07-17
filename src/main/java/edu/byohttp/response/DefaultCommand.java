package edu.byohttp.response;

import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;
import edu.byohttp.resource.ResourceManagerImpl;

import java.util.Optional;

public class DefaultCommand implements Command {
    private final NotImplementedResponseHandler notImplementedResponseHandler;
    private  final ResourceManagerImpl resourceManager;
    private static final String HTTP_NOT_IMPLEMENTED_RESOURCE_PATH = "501NotImplemented.html";

    public DefaultCommand(ResourceManagerImpl resourceManager,NotImplementedResponseHandler notImplementedResponseHandler){
        this.notImplementedResponseHandler = notImplementedResponseHandler;
        this.resourceManager = resourceManager;
    }

    @Override
    public Response execute(Request request) {
        Optional<Resource> notImplementedResource = resourceManager.searchResource(HTTP_NOT_IMPLEMENTED_RESOURCE_PATH);
        return notImplementedResponseHandler.handle(request,notImplementedResource.get());
    }
}
