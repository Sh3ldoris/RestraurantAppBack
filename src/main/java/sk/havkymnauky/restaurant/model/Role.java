package sk.havkymnauky.restaurant.model;

public enum Role {
    ADMIN("admin");
    public final String label;

    private Role(String label) {
        this.label = label;
    }

    public static Role valueOfLabel(String label) {
        for (Role e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
