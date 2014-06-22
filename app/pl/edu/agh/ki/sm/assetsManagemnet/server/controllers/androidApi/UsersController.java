package pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.androidApi;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.sm.assetsManagemnet.server.controllers.BaseController;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectDomainException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectEmailException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.UserService;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Marcin on 21.
 */
@org.springframework.stereotype.Controller
public class UsersController extends BaseController {

    @Autowired
    private UserService userService;

    public Result generateToken() throws IncorrectDomainException, IncorrectEmailException {
        String email = getHeader("email");
        userService.generateTokenForUser(email);
        return ok();
    }

}
