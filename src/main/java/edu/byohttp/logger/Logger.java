package edu.byohttp.logger;

import edu.byohttp.request.Request;
import edu.byohttp.response.Response;

public interface Logger {
    void log(Request request);
    void log(Response response);
}
