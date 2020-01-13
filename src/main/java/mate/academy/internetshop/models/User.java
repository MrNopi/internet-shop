package mate.academy.internetshop.models;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }
=======
    private String name;

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
