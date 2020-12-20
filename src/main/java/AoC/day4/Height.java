package AoC.day4;

import java.util.Optional;

import static AoC.util.AOCFunctions.map2;

public final class Height {

    private final int value;
    private final Unit unit;

    private Height(int value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static Optional<Height> from(String value) {
        if (value == null) {
            return Optional.empty();
        }
        final var c = value.indexOf('c');
        final var i = value.indexOf('i');
        return getHeight(value, c, i)
                .filter(Height::isValid);

    }

    private static boolean isValid(Height height) {
        return switch (height.getUnit()) {
            case CM -> height.getValue() > 149 && height.getValue() < 194;
            case IN -> height.getValue() > 58 && height.getValue() < 77;
        };
    }

    private static Optional<Height> getHeight(String value, int c, int i) {
        if (c > -1) {
            return parse(value, c);
        } else if (i > -1) {
            parse(value, i);
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private static Optional<Height> parse(String value, int c) {
        final var heightVal = value.substring(0, c);
        final var unitVal = value.substring(c);
        final var height = parseInt(heightVal);
        final var unit = Unit.of(unitVal);

        return map2(height, unit, integer -> unit1 -> new Height(integer, unit1));
    }

    public int getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    private static Optional<Integer> parseInt(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
