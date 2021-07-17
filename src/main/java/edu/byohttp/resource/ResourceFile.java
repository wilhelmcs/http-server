package edu.byohttp.resource;

import java.io.FileInputStream;

public class ResourceFile implements Resource {

    private final long contentLength;
    private final String contentType;
    private final String lastModifiedTime;
    private final FileInputStream resourceBytes;


    public ResourceFile(String contentType, String lastModifiedTime,
                        Long contentLength, FileInputStream resourceBytes) {
        this.contentType = contentType;
        this.lastModifiedTime = lastModifiedTime;
        this.contentLength = contentLength;
        this.resourceBytes = resourceBytes;
    }

    @Override
    public long getContentLength() {
        return this.contentLength;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public String getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    @Override
    public FileInputStream getResourceBytes() {
        return this.resourceBytes;
    }


}
