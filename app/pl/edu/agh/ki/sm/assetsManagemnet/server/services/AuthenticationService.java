package pl.edu.agh.ki.sm.assetsManagemnet.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.AuthorizationException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.model.UserService;

/**
 * Created by Marcin on 21.
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    public void authenticate(String token){
        if(!userService.userWithTokenExists(token)){
            throw new AuthorizationException();
        }
    }
}
