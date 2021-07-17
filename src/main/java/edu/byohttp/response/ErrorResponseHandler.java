package edu.byohttp.response;

import edu.byohttp.request.Request;
import edu.byohttp.resource.Resource;

public interface ErrorResponseHandler {
    Response handle(Request request, Resource resource);
}
