package edu.byohttp.resource;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class ResourceManagerImpl implements ResourceManager {

    MimeTypeManager mimeTypeManager;
    ResourceFactory resourceFactory;
    File resourcesDirectory;


    public ResourceManagerImpl(ResourceFactory resourceFactory, MimeTypeManagerImpl mimeTypeManager, File resourcesDirectory) {
        //TODO PATH FROM PARAMETER
        this.mimeTypeManager = mimeTypeManager;
        this.resourceFactory = resourceFactory;
        this.resourcesDirectory = resourcesDirectory;
    }

    @Override
    public Optional<Resource> searchResource(String searchedFilepath) {
        String resourcesDirectoryPath = resourcesDirectory.getAbsolutePath();
        try {
            File searchedFile = new File(resourcesDirectoryPath + "/" + searchedFilepath);
            String contentType = mimeTypeManager.getMimeType(searchedFile);
            String lastModified = formatLastModifiedTime(searchedFile);
            Long byteCount = countBytes(searchedFile);
            //TODO GET INPUT STREAM FROM SOMEWHERE
            FileInputStream bytes = new FileInputStream(searchedFile);

            Resource resource = ResourceFactory.getResource(lastModified, contentType, byteCount, bytes);
            return Optional.of(resource);

        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    private String formatLastModifiedTime(File file) {
        Date lastModified = new Date(file.lastModified());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        return simpleDateFormat.format(lastModified);
    }

    private Long countBytes(File file) {
        //TODO CHANGE TO STRING OR LEAVE INPUT STREAM IN DIAGRAM
        return file.length();
    }

}
