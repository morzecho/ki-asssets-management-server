package pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions;

import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.httpMappings.BadRequest;

/**
 * Created by Marcin on 21.
 */
public class IncorrectDomainException extends Exception implements BadRequest {

    public IncorrectDomainException(String email) {
        super("Domain '" + emailDomain(email) + "' is incorrect");
    }

    private static String emailDomain(String email){
        return email.substring(email.indexOf('@') + 1);
    }
}
