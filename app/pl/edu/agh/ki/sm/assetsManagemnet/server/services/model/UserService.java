package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.UserDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectDomainException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectEmailException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.User;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.MailerService;
import play.data.validation.Constraints;

/**
 * Created by Marcin on 21.
 */
@Service
public class UserService extends EntityService<User, UserDAO> {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MailerService mailerService;

    @Override
    protected UserDAO entityDAO() {
        return userDAO;
    }

    public void generateTokenForUser(String email) throws IncorrectDomainException, IncorrectEmailException {
        assertValidEmail(email);
        assertEmailInCorrectDomain(email);

        User user = userDAO.findByEmail(email);

        if (user == null) {
            user = new User(email);
        }

        user.generateToken();
        save(user);
        sendEmailWithToken(user);
    }

    private void assertValidEmail(String email) throws IncorrectEmailException {
        if (!Constraints.email().isValid(email)) {
            throw new IncorrectEmailException(email);
        }
    }

    private void assertEmailInCorrectDomain(String email) throws IncorrectDomainException {
        if (!email.endsWith("agh.edu.pl")) {
            throw new IncorrectDomainException(email);
        }
    }

    private void sendEmailWithToken(User user) {
        String text = "Your new token for AGH Service Tracker Application: " + user.getToken();
        String subject = "New Token Generated";
        mailerService.sendEmail(user.getEmail(), subject, text);
    }

    public boolean userWithTokenExists(String token) {
        return getByToken(token) != null;
    }

    public User getByToken(String token) {
        return userDAO.findByToken(token);
    }
}
