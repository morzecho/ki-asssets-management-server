package controllers;

import org.springframework.stereotype.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.BaseController;

@Controller
public class Application extends BaseController {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result login() {
        return ok(
            login.render()
        );
    }

}
