package edu.byohttp.request;
import java.util.HashMap;

public class Request {
    private String protocolVersion;
    private HashMap<String, String> headers;
    private ByoHttpMethod method;
    private String resource;

    public Request(String protocolVersion, HashMap<String, String> headers, ByoHttpMethod method, String resource) {
        this.protocolVersion= protocolVersion;
        this.headers = headers;
        this.method = method;
        this.resource= resource;
    }

    public ByoHttpMethod getMethod() {
        return method;
    }

    public String getResource() {
        return resource;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public String getHeaders(String headerName) {
        return headers.get(headerName);
    }

    public String toString(){
        return method + " " + "/" + resource + " " +  protocolVersion +
                "\n" + headersToString();
    }

    private String headersToString(){
        StringBuilder headersBuilder = new StringBuilder();
        for (String key: headers.keySet()){
            headersBuilder.append(key.toString()).append(" ").append(headers.get(key)).append("\n");
        }
        return headersBuilder.toString();
    }
}
