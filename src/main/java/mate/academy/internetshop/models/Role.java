package mate.academy.internetshop.models;

public class Role {
    private RoleName role;

    public Role(String role) {
        this.role = RoleName.valueOf(role);
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public enum RoleName {
        USER, ADMIN;
    }
}
