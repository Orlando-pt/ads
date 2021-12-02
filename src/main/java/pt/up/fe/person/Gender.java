package pt.up.fe.person;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
  MALE(1),
  FEMALE(2);

  private Integer value;
  private static Map<Integer, Gender> map = new HashMap<>();

  Gender(Integer value) {
    this.value = value;
  }

  static {
    for (Gender gender : Gender.values()) {
      map.put(gender.value, gender);
    }
  }

  public static Gender valueOf(Integer gender) {
    return (Gender) map.get(gender);
  }
}
