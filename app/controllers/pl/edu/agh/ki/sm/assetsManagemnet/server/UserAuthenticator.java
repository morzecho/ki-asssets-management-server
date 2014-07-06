package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by Marcin on 29.
 */
public class UserAuthenticator extends Security.Authenticator {
    public static final String USER_LOGIN = "userLogin";

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get(USER_LOGIN);
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.Application.login());
    }
}
