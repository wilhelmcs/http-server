package edu.byohttp.logger;

import edu.byohttp.request.Request;
import edu.byohttp.response.Response;

public class ConsoleLogger implements Logger {

    @Override
    public void log(Request request) {
        System.out.println(request.toString());
    }

    @Override
    public void log(Response response) {
        System.out.println(response.toString());
    }
}
