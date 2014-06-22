package pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions;

/**
 * Created by Marcin on 21.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
