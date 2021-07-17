package edu.byohttp;

import edu.byohttp.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class SocketMessageRunnable implements Runnable {

    private final InputStream in;
    private final OutputStream out;
    private RequestProcessorImpl requestProcessor;

    public SocketMessageRunnable(Socket socket, RequestProcessorImpl requestProcessor) throws IOException {
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.requestProcessor = requestProcessor;
    }

    @Override
    public void run() {
        try {
            String message = inputStreamToString();
            if ("".equals(message)) {
                return;
            } else {
                Response response = requestProcessor.getAResponse(message);
                out.write(response.toString().getBytes());
                writeResourceBytes(response.getResource().get());
                in.close();
                out.flush();
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException("Unable to write on OutputStream");
        }

    }

    private void writeResourceBytes(InputStream in) throws IOException {
        int count;
        byte[] buffer = new byte[1024 * 40]; // or 4096, or more
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
    }


    private String inputStreamToString() throws IOException {
        StringBuilder result = new StringBuilder();
        do {
            int read = in.read();
            if (read != -1) {
                result.append((char) read);
            }
        } while (in.available() > 0);
        return result.toString();
    }

}
