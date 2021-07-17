package edu.byohttp.resource;

import java.io.FileInputStream;

public class ResourceFactory {


    public static Resource getResource(String lastModifiedTime, String contentType,
                                       Long contentLength, FileInputStream bytes) {

        return new ResourceFile(contentType, lastModifiedTime, contentLength, bytes);
    }
}
