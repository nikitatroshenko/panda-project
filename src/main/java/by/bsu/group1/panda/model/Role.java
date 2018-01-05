package by.bsu.group1.panda.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Role {
    DEVELOPER(1, "Developer"),
    MANAGER(2, "Manager"),
    UNKNOWN(-1, "Unknown");

    private final long id;
    private final String name;

    Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public static Role forId(long id) {
        return Arrays.stream(values())
                .filter(role -> role.getId() == id)
                .findAny()
                .orElse(UNKNOWN);
    }

    @JsonCreator
    public static Role forName(String name) {
        return Arrays.stream(values())
                .filter(role -> role.getName().equals(name))
                .findAny()
                .orElse(UNKNOWN);
    }
}
