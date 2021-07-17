package edu.byohttp.response;


import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class Response {
     private HttpResponseStatusCode statusCode;
     private String protocolVersion;
     private Map<String, String> headers;
     private Optional<InputStream> resource;
     private static final String CRLF = "\r\n";


     public Response(HttpResponseStatusCode statusCode, String protocolVersion, Map<String, String> headers, Optional<InputStream> resource){
         this.statusCode = statusCode;
         this.protocolVersion = protocolVersion;
         this.headers = headers;
         this.resource = resource;
     }

     public String getHeader(String key){
         return headers.get(key);
     }

     public int getStatusCode(){
         return statusCode.getCode();
     }

     @Override
     public String toString(){
         return protocolVersion + " " + statusCode.toString()
            + "\n" + headersToString() +CRLF+CRLF;
     }

     private String headersToString(){
         StringBuilder headersBuilder = new StringBuilder();
         for (String key: headers.keySet()){
             headersBuilder.append(key.toString()).append(": ").append(headers.get(key)).append("\n");
         }
         return headersBuilder.toString();
     }

     public Optional<InputStream> getResource(){ return resource; }
}
