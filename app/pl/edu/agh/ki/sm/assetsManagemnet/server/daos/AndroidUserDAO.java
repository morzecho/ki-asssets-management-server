package pl.edu.agh.ki.sm.assetsManagemnet.server.daos;

import org.springframework.stereotype.Repository;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.AndroidUser;

/**
 * Created by Marcin on 21.
 */
@Repository
public class AndroidUserDAO extends EntityDAO<AndroidUser> {

    public AndroidUserDAO() {
        super(AndroidUser.class);
    }

    public AndroidUser findByEmail(String email) {
        return find().where().eq("email", email).findUnique();
    }

    public AndroidUser findByToken(String token){
        return find().where().eq("token", token).findUnique();
    }
}
