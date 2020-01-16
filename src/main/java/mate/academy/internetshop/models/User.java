package mate.academy.internetshop.models;

import java.util.UUID;

public class User {
    private Long id;
    private String login;
    private String surname;
    private String password;
    private UUID token;

    public String getToken() {
        return token.toString();
    }

    public void setToken() {
        token = UUID.nameUUIDFromBytes((login + password).getBytes());
    }

    public User(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
