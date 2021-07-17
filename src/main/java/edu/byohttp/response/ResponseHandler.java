package edu.byohttp.response;

import edu.byohttp.request.Request;

public interface ResponseHandler {
    Response buildResponse(Request request);
}
