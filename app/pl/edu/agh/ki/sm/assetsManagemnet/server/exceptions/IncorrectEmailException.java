package pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions;

import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.httpMappings.BadRequest;

/**
 * Created by Marcin on 21.
 */
public class IncorrectEmailException extends Exception implements BadRequest {

    public IncorrectEmailException(String email) {
        super("Email: " + email + " is incorrect");
    }
}
