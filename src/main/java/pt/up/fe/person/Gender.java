package pt.up.fe.person;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
  MALE(1), FEMALE(2);

  private static final Map map = new HashMap<>();

  static {
    for (Gender gender : Gender.values()) {
      map.put(gender.value, gender);
    }
  }

  private final int value;

  Gender(int value) {
    this.value = value;
  }

  public static Gender valueOf(int gender) {
    return (Gender) map.get(gender);
  }
}

