package pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions;

/**
 * Created by Marcin on 22.
 */
public class ExternalSystemException extends RuntimeException {

    public ExternalSystemException(String message, Throwable t) {
        super(message, t);
    }
}
