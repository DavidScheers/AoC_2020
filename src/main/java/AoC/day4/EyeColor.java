package AoC.day4;

import java.util.Arrays;
import java.util.Optional;

public enum EyeColor {

    AMB("amb"), BLU("blu"), BRN("brn"), GRY("gry"), GRN("grn"), HZL("hzl"), OTH("oth");

    private final String value;

    EyeColor(String eyc) {
        this.value = eyc;
    }

    public String getValue() {
        return value;
    }

    public static Optional<EyeColor> from(String value) {
        return Arrays.stream(EyeColor.values())
                .filter(eyeColor -> eyeColor.getValue().equals(value))
                .findAny();
    }
}
