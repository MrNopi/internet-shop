package mate.academy.internetshop.models;

public class Role {
    private roleName role;

    public Role(String role) {
        this.role = roleName.valueOf(role);
    }

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
