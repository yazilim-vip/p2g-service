package vip.yazilim.p2g.web.constant.enums;

import java.util.HashMap;

public enum Role {
    UNDEFINED("Undefined"),
    P2G_USER("P2G_User"),
    ROOM_USER("User"),
    ROOM_MODERATOR("Moderator"),
    ROOM_ADMIN("Admin"),
    ROOM_OWNER("Owner");

    private static final HashMap<String, Role> map = new HashMap<>();
    public final String role;

    Role(String role) {
        this.role = role;
    }

    public static Role getRole(String role) {
        return map.get(role);
    }

    public String getRole() {
        return this.role;
    }

    static {
        for (Role role : values()) {
            map.put(role.role, role);
        }
    }
}
