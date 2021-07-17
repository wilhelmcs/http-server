package edu.byohttp.response;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class ResponseFactory {

    public static Response getResponse(HttpResponseStatusCode statusCode, String protocolVersion, Map<String, String> headers, Optional<InputStream> resource){
        return new Response(statusCode, protocolVersion, headers, resource);
    }

}