package pl.edu.agh.ki.sm.assetsManagemnet.server.controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
