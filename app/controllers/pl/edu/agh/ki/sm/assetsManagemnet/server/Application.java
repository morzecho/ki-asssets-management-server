package controllers.pl.edu.agh.ki.sm.assetsManagemnet.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.UserService;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import views.html.index;
import views.html.login;

import static play.data.Form.form;

@Controller
public class Application extends BaseController {

    @Autowired
    private UserService userService;

    @Security.Authenticated(UserAuthenticator.class)
    public Result index() {
        return ok(index.render());
    }

    public Result login() {
        return ok(
            login.render(form(UserLogin.class))
        );
    }

    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Application.login());
    }

    public Result authenticate() {
        Form<UserLogin> loginForm = form(UserLogin.class).bindFromRequest();

        UserLogin userLogin = loginForm.get();
        if(userService.authenticate(userLogin.login, userLogin.password)){
            session().clear();
            session(UserAuthenticator.USER_LOGIN, userLogin.login);
            return redirect(routes.Application.index());
        }else {
            loginForm.get().setError("Invalid login or password");
            return badRequest(login.render(loginForm));
        }
    }

    public static class UserLogin {
        public String login;
        public String password;
        public boolean hasError;
        public String error;

        void setError(String error){
            this.hasError = true;
            this.error = error;
        }
    }

}
