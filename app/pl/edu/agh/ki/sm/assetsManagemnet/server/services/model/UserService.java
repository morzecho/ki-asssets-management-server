package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import controllers.pl.edu.agh.ki.sm.assetsManagemnet.server.Application;
import org.springframework.stereotype.Service;
import play.Play;

/**
 * Created by Marcin on 29.
 */
@Service
public class UserService {
    private static final String LOGIN = Play.application().configuration().getString("user.login");
    private static final String PASSWORD = Play.application().configuration().getString("user.password");

    public boolean authenticate(String login, String password){
        return LOGIN.equals(login) && PASSWORD.equals(password);
    }
}
