package pt.up.fe.person;

import java.util.HashMap;
import java.util.Map;

public enum Genre {
    MALE(1), FEMALE(2);

    private int value;
    private static Map map = new HashMap<>();

    Genre(int value) {
        this.value = value;
    }

    static {
        for (Genre genre : Genre.values()) {
            map.put(genre.value, genre);
        }
    }

    public static Genre valueOf(int genre) {
        return (Genre) map.get(genre);
    }
}

