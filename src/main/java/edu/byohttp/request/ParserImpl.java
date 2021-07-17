package edu.byohttp.request;

import java.util.HashMap;
import java.util.StringTokenizer;

public class ParserImpl implements Parser {

    private final RequestFactory requestFactory;

    public ParserImpl(RequestFactory requestFactory){
        this.requestFactory = requestFactory;

    }
    @Override
    public Request parseRequest(String message){
        try{
        if (message == null || message.equals("")){
            throw new IllegalArgumentException();
        }
            HashMap<String, String> headersMap = new HashMap<>();
            HashMap<String, ByoHttpMethod> methodsMap = new HashMap<>();
            String[] partsMessage = message.split("\r\n");
            StringTokenizer tokenSpace = new StringTokenizer(partsMessage[0], " ");
            String method = "";
            String source = "";
            String protocolVersion = "";
            int tokenSpaceiterator = 0;
            int lineIterator = 1;
            while (tokenSpace.hasMoreTokens()) {
                String tokenPart = tokenSpace.nextToken();
                if (tokenSpaceiterator == 0) {
                    method = tokenPart;

                } else if (tokenSpaceiterator == 1) {
                    source = tokenPart.replace("/", "");
                } else if (tokenSpaceiterator == 2) {
                    protocolVersion = tokenPart.replace("\r", "");
                }
                tokenSpaceiterator++;

            }
            while (lineIterator < partsMessage.length) {
                StringTokenizer tokenSpaceHeader = new StringTokenizer(partsMessage[lineIterator], " ");
                StringTokenizer tokenSpaceHeaderEncoding = new StringTokenizer(partsMessage[lineIterator], ":");
                int subLineIterator = 0;
                String title = "";
                String description = "";
                while (tokenSpaceHeader.hasMoreTokens()) {
                    String tokenPart = tokenSpaceHeader.nextToken();
                    if (subLineIterator == 0) {
                        title = tokenPart;
                    } else if (subLineIterator == 1) {
                        if (lineIterator == 5) {
                            tokenSpaceHeaderEncoding.nextToken();
                            while (tokenSpaceHeaderEncoding.hasMoreTokens()) {
                                description = tokenSpaceHeaderEncoding.nextToken().replaceFirst(" ", "");
                            }
                        } else {
                            description = tokenPart.replace("\r", "");
                        }
                    }
                    headersMap.put(title, description);
                    subLineIterator++;
                }
                lineIterator++;
            }
            methodsMap.put("GET", ByoHttpMethod.GET);
            methodsMap.put("HEAD", ByoHttpMethod.HEAD);
            methodsMap.put("PUT", ByoHttpMethod.PUT);
            methodsMap.put("CONNECT", ByoHttpMethod.CONNECT);
            methodsMap.put("DELETE", ByoHttpMethod.DELETE);
            methodsMap.put("OPTIONS", ByoHttpMethod.OPTIONS);
            methodsMap.put("PATCH", ByoHttpMethod.PATCH);
            methodsMap.put("POST", ByoHttpMethod.POST);
            methodsMap.put("TRACE", ByoHttpMethod.TRACE);

            return requestFactory.getRequest(protocolVersion, headersMap, methodsMap.get(method), source);
        } catch (IllegalArgumentException e){
            System.out.println ("NULL MESSAGE");
            HashMap<String, String> headersMap = new HashMap<>();
            return requestFactory.getRequest("", headersMap,ByoHttpMethod.NULL, "");
        }

    }
}

