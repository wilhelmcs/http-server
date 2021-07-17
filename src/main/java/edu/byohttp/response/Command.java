package edu.byohttp.response;

import edu.byohttp.request.Request;

public interface Command {

    Response execute(Request request);
}
