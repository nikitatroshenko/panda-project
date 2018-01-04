package by.bsu.group1.panda.model;

import java.util.Arrays;

public enum Role {
    DEVELOPER(1, "Developer"),
    MANAGER(2, "Manager");

    private final long id;
    private final String name;

    Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Role forId(long id) {
        return Arrays.stream(values())
                .filter(role -> role.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Illegal role id '" + id + '\''));
    }

    public static Role forName(String name) {
        return Arrays.stream(values())
                .filter(role -> role.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Illegal role name '" + name + '\''));
    }
}
