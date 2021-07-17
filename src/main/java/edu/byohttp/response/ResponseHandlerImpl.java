package edu.byohttp.response;

import edu.byohttp.request.ByoHttpMethod;
import edu.byohttp.request.Request;

import java.util.HashMap;

public class ResponseHandlerImpl implements ResponseHandler{

    private HashMap<ByoHttpMethod, Command> commands = new HashMap<ByoHttpMethod, Command>();

    public ResponseHandlerImpl(HashMap<ByoHttpMethod, Command> commands){
        this.commands = commands;
    }

    @Override
    public Response buildResponse(Request request) {
        Command command = commands.get(request.getMethod());
        return command.execute(request);
    }
}
