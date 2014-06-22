package pl.edu.agh.ki.sm.assetsManagemnet.server.daos;

import org.springframework.stereotype.Repository;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;

/**
 * Created by Marcin on 21.
 */
@Repository
public class UserDAO extends EntityDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public User findByEmail(String email) {
        return find().where().eq("email", email).findUnique();
    }

    public User findByToken(String token){
        return find().where().eq("token", token).findUnique();
    }
}
