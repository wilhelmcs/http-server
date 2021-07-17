package edu.byohttp;

import edu.byohttp.logger.ConsoleLogger;
import edu.byohttp.request.ByoHttpMethod;
import edu.byohttp.request.ParserImpl;
import edu.byohttp.request.Request;
import edu.byohttp.response.Response;
import edu.byohttp.response.ResponseHandlerImpl;

public class RequestProcessorImpl implements RequestProcessor {

    private final ParserImpl parser;
    private final ResponseHandlerImpl responseHandler;
    private final ConsoleLogger logger;
    private final InternalServerResponseHandlerImpl internalServerResponseHandler;

    public RequestProcessorImpl(ParserImpl parser, ResponseHandlerImpl responseHandler, ConsoleLogger logger, InternalServerResponseHandlerImpl internalServerResponseHandler) {
        this.parser = parser;
        this.responseHandler = responseHandler;
        this.logger = logger;
        this.internalServerResponseHandler = internalServerResponseHandler;
    }

    @Override
    public Response getAResponse(String requestStr) {
        Request request = parser.parseRequest(requestStr);
        logger.log(request);
        Response response;
        if (request.getMethod() == ByoHttpMethod.NULL) {
            response = internalServerResponseHandler.handle(request);
        } else {
            response = responseHandler.buildResponse(request);
        }
        logger.log(response);
        return response;
    }
}
