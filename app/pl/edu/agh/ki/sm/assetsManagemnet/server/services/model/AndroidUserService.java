package pl.edu.agh.ki.sm.assetsManagemnet.server.services.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.sm.assetsManagemnet.server.daos.AndroidUserDAO;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.AuthorizationException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectDomainException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.exceptions.IncorrectEmailException;
import pl.edu.agh.ki.sm.assetsManagemnet.server.model.AndroidUser;
import pl.edu.agh.ki.sm.assetsManagemnet.server.services.MailerService;
import play.data.validation.Constraints;

/**
 * Created by Marcin on 21.
 */
@Service
public class AndroidUserService extends EntityService<AndroidUser, AndroidUserDAO> {

    @Autowired
    private AndroidUserDAO androidUserDAO;

    @Autowired
    private MailerService mailerService;

    @Override
    protected AndroidUserDAO entityDAO() {
        return androidUserDAO;
    }

    public void generateTokenForUser(String email) throws IncorrectDomainException, IncorrectEmailException {
        assertValidEmail(email);
        assertEmailInCorrectDomain(email);

        AndroidUser androidUser = androidUserDAO.findByEmail(email);

        if (androidUser == null) {
            androidUser = new AndroidUser(email);
        }

        androidUser.generateToken();
        save(androidUser);
        sendEmailWithToken(androidUser);
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

    private void sendEmailWithToken(AndroidUser androidUser) {
        String text = "Your new token for AGH Service Tracker Application: " + androidUser.getToken();
        String subject = "New Token Generated";
        mailerService.sendEmail(androidUser.getEmail(), subject, text);
    }

    public AndroidUser getByToken(String token) {
        return androidUserDAO.findByToken(token);
    }

    public AndroidUser authenticate(String token){
        AndroidUser androidUser = getByToken(token);

        if(androidUser == null){
            throw new AuthorizationException();
        }

        return androidUser;
    }

    public void deleteToken(AndroidUser androidUser) {
        androidUser.deleteToken();
        save(androidUser);
    }
}
