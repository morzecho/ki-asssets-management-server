package pl.edu.agh.ki.sm.assetsManagemnet.server.services;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;
import org.springframework.stereotype.Service;

/**
 * Created by Marcin on 21.
 */
@Service
public class MailerService {

    private MailerPlugin mailerPlugin = play.Play.application().plugin(MailerPlugin.class);

    public void sendEmail(String recipientEmail, String subject, String text){
        MailerAPI mail = mailerPlugin.email();

        mail.setRecipient(recipientEmail);
        mail.setSubject(subject);
        mail.setFrom("KI AGH Assets Management <kiaghassetsmanagement@gmail.com>");
        mail.send(text);
    }
}
