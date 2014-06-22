package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by Marcin on 21.
 */
@javax.persistence.Entity
@Table(name = "MANAGEMENT_USER")
public class User extends Entity {

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    public User(String email) {
        this.email = email;
    }

    public void generateToken(){
        token = UUID.randomUUID().toString();
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
