package pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions;

import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.httpMappings.Unauthorized;

/**
 * Created by Marcin on 21.
 */
public class AuthorizationException extends RuntimeException implements Unauthorized {

    public AuthorizationException() {
        super("Unauthorized request");
    }
}
