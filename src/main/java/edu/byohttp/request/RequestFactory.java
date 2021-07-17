package edu.byohttp.request;

import java.util.HashMap;

public class RequestFactory {

    public static Request getRequest(String protocolVersion, HashMap<String, String> headers, ByoHttpMethod method, String resource){
        Request request = new Request(protocolVersion, headers, method, resource);
        return request;
    }
}
