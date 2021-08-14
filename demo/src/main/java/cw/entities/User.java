package cw.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class User {
    private @GeneratedValue
    @Id
    @Getter
    @Setter
    Long id;
    private @Setter
    @Getter
    String firstname;
    private @Setter
    @Getter
    String lastname;
    private @Setter
    @Getter
    String name;
    private @Getter
    @Setter
    String phone;
    private @Setter
    @Getter
    boolean active;
    private @Setter
    @Getter
    boolean isAdmin;

    public User() {

    }

    User(Long id, String firstname, String lastname, String name, String phone, boolean active, boolean isAdmin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.isAdmin = isAdmin;
    }
}
