package AoC.day4;

import java.util.Optional;

public final class PassportId {

    private final Integer id;

    private PassportId(Integer id) {
        this.id = id;
    }

    public static Optional<PassportId> from(String value) {
        if (value == null) {
            return Optional.empty();
        }
        try {
            if (value.matches("[0-9]+") && value.length() == 9) {
                final var id = Integer.parseInt(value);
                return Optional.of(new PassportId(id));
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public Integer getId() {
        return id;
    }
}
