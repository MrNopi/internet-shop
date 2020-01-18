package mate.academy.internetshop.models;

public class Role {
    private roleName role;

//    private Role() {
//        ++id;
//    }

    public Role(String role) {
//        this();
        this.role = roleName.valueOf(role);
    }

//    public Long getId() {
//        return id;
//    }

    public roleName getRole() {
        return role;
    }

    public void setRole(roleName role) {
        this.role = role;
    }

    public enum roleName {
        USER, ADMIN;
    }
}
