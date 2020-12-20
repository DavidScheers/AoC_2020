package AoC.day4;

import java.util.Arrays;
import java.util.Optional;

public enum Unit {

    CM("cm"),
    IN("in");

    private final String value;

    Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Unit> of(String value) {
        try {
            return Arrays.stream(Unit.values())
                    .filter(unit -> unit.getValue().equals(value))
                    .findAny();
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
