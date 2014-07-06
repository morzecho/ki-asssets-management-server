package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.androidApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.BaseController;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.AuthorizationException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectDomainException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectEmailException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.AndroidUser;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.AndroidUserService;
import play.libs.Json;
import play.mvc.Result;

/**
 * Created by Marcin on 21.
 */
@Controller
public class UsersController extends BaseController {

    @Autowired
    private AndroidUserService androidUserService;

    public UsersController() {
        super();
    }

    public Result checkIfTokenIsValid() {
        try {
            authenticateByToken();
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
        AndroidUser androidUser = authenticateByToken();

        androidUserService.deleteToken(androidUser);
        return ok();
    }

    public Result generateToken() throws IncorrectDomainException, IncorrectEmailException {
        String email = getHeader("email");
        androidUserService.generateTokenForUser(email);
        return ok();
    }

}
