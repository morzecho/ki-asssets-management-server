package pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions;

import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.httpMappings.BadRequest;

/**
 * Created by Marcin on 21.
 */
public class HeaderMissedException extends RuntimeException implements BadRequest {

    public HeaderMissedException(String header) {
        super("Header '" + header + "' is required");
    }
}
