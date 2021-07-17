package edu.byohttp.resource;

import java.util.Optional;

public interface ResourceManager {

    Optional<Resource> searchResource(String path);

}
