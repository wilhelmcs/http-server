package edu.byohttp;

import edu.byohttp.logger.ConsoleLogger;
import edu.byohttp.request.ByoHttpMethod;
import edu.byohttp.request.ParserImpl;
import edu.byohttp.request.RequestFactory;
import edu.byohttp.resource.MimeTypeManagerImpl;
import edu.byohttp.resource.ResourceFactory;
import edu.byohttp.resource.ResourceManagerImpl;
import edu.byohttp.response.*;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;

public class ByoHttp {

    private final int port;
    private final File resourcesDirectory;
    private final File mimeTypesAssociation;

    ByoHttp(int port, File resourcesDirectory, File mimeTypesAssociation ) {
        this.port = port;
        this.resourcesDirectory = resourcesDirectory;
        this.mimeTypesAssociation = mimeTypesAssociation;
    }

    void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            while (true) {
                System.out.println("Se dispara hilo");
                new Thread(new SocketMessageRunnable(serverSocket.accept(), requestProcessor())).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + this.port);
            System.exit(-1);
        }
    }

    public static void main(String... args) {
        if (!validateArgsCount(args)) {
            System.exit(1);
        }

        OptionalInt port = validatePort(args[0]);
        if (port.isEmpty()) {
            System.exit(1);
        }

        Optional<File> resourcePath = validateResourcePath(args[1]);
        if (resourcePath.isEmpty()) {
            System.exit(1);
        }

        Optional<File> mimeAssociationPath = validateMimeAssociationPath(args[2]);
        if (mimeAssociationPath.isEmpty()) {
            System.exit(1);
        }

        ByoHttp app = new ByoHttp(port.getAsInt(), resourcePath.get(), mimeAssociationPath.get());
        app.run();
    }

    private static Optional<File> validateResourcePath(String resourcePathArg) {
        File resourcePath = new File(resourcePathArg);
        if (!resourcePath.exists() || !resourcePath.isDirectory()) {
            System.err.println("Argument <resources path> should point to a directory");
            return Optional.empty();
        }
        return Optional.of(resourcePath);
    }

    private static OptionalInt validatePort(String portArg) {
        try {
            int port = Integer.parseUnsignedInt(portArg);
            return OptionalInt.of(port);
        } catch (NumberFormatException nfe) {
            System.err.println("Argument <port number> should be a number");
            return OptionalInt.empty();
        }
    }

    private static Optional<File> validateMimeAssociationPath(String mimeAssociationPathArg) {
        File mimeAssociationPath = new File(mimeAssociationPathArg);
        if (!mimeAssociationPath.exists() || !mimeAssociationPath.isFile()) {
            System.err.println("Argument <mime association path> should point to a file");
            return Optional.empty();
        }
        return Optional.of(mimeAssociationPath);
    }

    private static boolean validateArgsCount(String[] args) {
        if (args.length != 3) {
            printUsage();
            System.err.println("Invalid number of arguments");
            return false;
        }
        return true;
    }

    private static void printUsage() {
        System.out.println("Usage: java ByoHttp <port number> <resources path>");
    }

    private RequestFactory requestFactory(){
        return new RequestFactory();
    }

    private  ResponseFactory responseFactory(){
        return new ResponseFactory();
    }

    private ParserImpl parserImpl(){
        return new ParserImpl(requestFactory());
    }

    private ResponseHandlerImpl responseHandlerImpl(){
        HashMap<ByoHttpMethod, Command> commands = new HashMap<ByoHttpMethod, Command>();
        commands.put(ByoHttpMethod.GET, new GetMethodCommand(resourceManagerImpl(), responseFactory(), notFoundResponseHandler()));
        commands.put(ByoHttpMethod.HEAD, new HeadMethodCommand(resourceManagerImpl(), responseFactory(), notFoundResponseHandler()));
        commands.put(ByoHttpMethod.PUT, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));
        commands.put(ByoHttpMethod.DELETE, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));
        commands.put(ByoHttpMethod.CONNECT, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));
        commands.put(ByoHttpMethod.OPTIONS, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));
        commands.put(ByoHttpMethod.PATCH, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));
        commands.put(ByoHttpMethod.POST, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));
        commands.put(ByoHttpMethod.TRACE, new DefaultCommand(resourceManagerImpl(), notImplementedResponseHandler()));

        return new ResponseHandlerImpl(commands);
    }

    private ResourceManagerImpl resourceManagerImpl(){
        return  new ResourceManagerImpl(resourceFactory(), mimeTypeManager(),resourcesDirectory);
    }

    private ResourceFactory resourceFactory(){
        return new ResourceFactory();
    }

    private NotFoundResponseHandler notFoundResponseHandler(){
        return new NotFoundResponseHandler(responseFactory());
    }

    private NotImplementedResponseHandler notImplementedResponseHandler(){
        return new NotImplementedResponseHandler(responseFactory());
    }

    private InternalServerResponseHandlerImpl internalServerResponseHandler(){
        return new InternalServerResponseHandlerImpl(resourceManagerImpl(), responseFactory());
    }

    private ConsoleLogger consoleLogger(){
        return new ConsoleLogger();
    }

    private MimeTypeManagerImpl mimeTypeManager(){
        return new MimeTypeManagerImpl(mimeTypesAssociation);
    }

    private RequestProcessorImpl requestProcessor(){
        return new RequestProcessorImpl(parserImpl(), responseHandlerImpl(), consoleLogger(), internalServerResponseHandler());
    }

}


