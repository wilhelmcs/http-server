package edu.byohttp.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MimeTypeManagerImpl implements MimeTypeManager {

    private final Map<String, String> mimeTypes;


    public MimeTypeManagerImpl(File mimeTypesAssociation) {
        mimeTypes = new HashMap<String, String>();
        buildMimeTypesAssociation(mimeTypesAssociation);
    }

    private void buildMimeTypesAssociation(File mimeTypesAssociation) {

        try {
            Scanner reader = new Scanner(mimeTypesAssociation);
            int index;
            while (reader.hasNextLine()) {
                String mimeAssociationLine = reader.nextLine();
                index = mimeAssociationLine.lastIndexOf(",");
                String extension = mimeAssociationLine.substring(0, index);
                String contentType = mimeAssociationLine.substring(index + 1);
                mimeTypes.put(extension, contentType);
            }
            reader.close();
        } catch (FileNotFoundException fne) {
            //TODO THROW A INTERNAL SERVER
            fne.printStackTrace();
        }

    }

    @Override
    public String getMimeType(File file) {
        String filename = file.getName();
        int index = filename.lastIndexOf('.');
        String extension;
        if (index < 1 || index == filename.length() - 1) {
            extension = ".";
        } else {
            extension = filename.substring(index);
        }
        return this.mimeTypes.get(extension);
    }


}

