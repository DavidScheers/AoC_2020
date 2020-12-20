package AoC.day4;

import java.util.Optional;

public final class Year {

    private final int value;

    Year(int value) {
        this.value = value;
    }

    public static Optional<Year> from(String value) {
        if (value == null) {
            return Optional.empty();
        }

        try {
            if (value.matches("[0-9]+") && value.length() == 4) {
                final var year = Integer.parseInt(value);
                return Optional.of(new Year(year));
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public int getValue() {
        return value;
    }

    public boolean greaterThan(Year other) {
        return this.value > other.getValue();
    }

    public boolean smallerThan(Year other) {
        return this.value < other.getValue();
    }
}
