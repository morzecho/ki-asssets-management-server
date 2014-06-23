package pl.edu.agh.ki.sm.assetsManagemnet.server.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by Marcin on 21.
 */
@javax.persistence.Entity
@Table(name = "ANDROID_USER")
public class User extends Entity {

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "EXTERNAL_SYSTEM_ID", unique = true)
    private Integer externalSystemId;

    public User(String email) {
        this.email = email;
    }

    @Override
    public Long getId() {
        return super.getId();
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

    public Integer getExternalSystemId() {
        return externalSystemId;
    }

    public void updateExternalSystemId(Integer externalSystemId) {
        this.externalSystemId = externalSystemId;
    }
}
