package edu.byohttp;

import edu.byohttp.request.Request;
import edu.byohttp.response.Response;

public interface InternalServerErrorHandler {
    Response handle(Request request);
}
