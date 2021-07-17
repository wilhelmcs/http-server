package edu.byohttp.resource;

import java.io.InputStream;

public interface Resource {

    long getContentLength();

    String getContentType();

    String getLastModifiedTime();

    InputStream getResourceBytes();

}
