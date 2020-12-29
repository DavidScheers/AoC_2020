package AoC.day4;

import java.util.Optional;
import java.util.regex.Pattern;

public final class HairColor {

    private static final Pattern PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6})$");

    private final String hairColor;

    private HairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public static Optional<HairColor> from(String value) {
        return value == null
                ? Optional.empty()
                : value.matches(PATTERN.pattern()) ? Optional.of(new HairColor(value)) : Optional.empty();
    }

    public static Pattern getPATTERN() {
        return PATTERN;
    }

    public String getHairColor() {
        return hairColor;
    }
}
