package edu.byohttp.response;

public enum HttpResponseStatusCode {
    INTERNAL_SERVER_ERROR("Internal Server Error", 500),
    NOT_IMPLEMENTED_ERROR("Not Implemented Error", 501),
    NOT_FOUND("Not Found", 404),
    OK("OK", 200);

    private final String error;
    private final int code;

    HttpResponseStatusCode(String error, int code){
        this.error = error;
        this.code = code;
    }

    public String toString(){
        return code + " " + error;
    }

    public int getCode(){
        return code;
    }
}
