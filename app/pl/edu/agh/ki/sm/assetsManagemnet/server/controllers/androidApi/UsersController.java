package pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.androidApi;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.BaseController;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.AuthorizationException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectDomainException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectEmailException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.UserService;
import play.libs.Json;
import play.mvc.Result;

/**
 * Created by Marcin on 21.
 */
@org.springframework.stereotype.Controller
public class UsersController extends BaseController {

    @Autowired
    private UserService userService;

    public UsersController() {
        super();
    }

    public Result checkIfTokenIsValid() {
        try {
            authenticate();
            return ok(Json.toJson(TokenValidness.validToken));
        } catch (AuthorizationException e) {
            return ok(Json.toJson(TokenValidness.invalidToken));
        }
    }

    private static class TokenValidness {
        private boolean tokenValid;

        private static TokenValidness validToken = new TokenValidness(true);
        private static TokenValidness invalidToken = new TokenValidness(false);

        private TokenValidness(boolean tokenValid) {
            this.tokenValid = tokenValid;
        }

        public boolean isTokenValid() {
            return tokenValid;
        }
    }

    public Result deleteToken() {
        User user = authenticate();

        userService.deleteToken(user);
        return ok();
    }

    public Result generateToken() throws IncorrectDomainException, IncorrectEmailException {
        String email = getHeader("email");
        userService.generateTokenForUser(email);
        return ok();
    }

}
