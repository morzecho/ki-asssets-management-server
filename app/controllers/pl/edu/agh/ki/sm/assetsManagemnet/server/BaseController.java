package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.HeaderMissedException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.UserService;
import play.libs.Json;
import play.mvc.Controller;

/**
 * Created by Marcin on 21.
 */
public abstract class BaseController extends Controller {

    @Autowired
    private UserService userService;

    protected String getHeader(String header){
        String headerValue = request().getHeader(header);

        if(headerValue == null){
            throw new HeaderMissedException(header);
        }

        return headerValue;
    }

    protected User authenticate(){
        String token = getHeader("token");
        return userService.authenticate(token);
    }

    protected <T> T fromJson(Class<T> tClass) {
        JsonNode jsonNode = request().body().asJson();
        return Json.fromJson(jsonNode, tClass);
    }
}
