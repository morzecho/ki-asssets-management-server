package pl.edu.agh.ki.sm.assetsManagemnet.server.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.HeaderMissedException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.AuthenticationService;
import play.libs.Json;
import play.mvc.Controller;

import java.io.IOException;

/**
 * Created by Marcin on 21.
 */
public abstract class BaseController extends Controller {

    @Autowired
    private AuthenticationService authenticationService;

    protected String getHeader(String header){
        String headerValue = request().getHeader(header);

        if(headerValue == null){
            throw new HeaderMissedException(header);
        }

        return headerValue;
    }

    protected void authenticate(){
        String token = getHeader("token");
        authenticationService.authenticate(token);
    }

    protected <T> T fromJson(Class<T> tClass) {
        JsonNode jsonNode = request().body().asJson();
        return Json.fromJson(jsonNode, tClass);
    }
}
