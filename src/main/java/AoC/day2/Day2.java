package AoC.day2;

import AoC.util.Tuple;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.Optional;

public final class Day2 {

    public Single<Long> run1(Flowable<String> inputs) {
        return inputs.map(this::parse)
                .map(PasswordPolicy::isValid)
                .filter(Optional::isPresent)
                .count();
    }

    private PasswordPolicy parse(String input) {
        final var boundsSplit = input.indexOf('-');
        final var space = input.indexOf(' ');
        final var firstBound = Integer.parseInt(input.substring(0, boundsSplit));
        final var secondBound = Integer.parseInt(input.substring(boundsSplit + 1, space));
        final var character = input.substring(space + 1, input.indexOf(':')).charAt(0);
        final var password = input.substring(input.indexOf(": "));
        return new PasswordPolicy(Tuple.of(firstBound, secondBound), character, password);
    }


    public static class PasswordPolicy {
        private final Tuple<Integer, Integer> bounds;
        private final char character;
        private final String password;

        public PasswordPolicy(Tuple<Integer, Integer> bounds, char character, String password) {
            this.bounds = bounds;
            this.character = character;
            this.password = password;
        }

        public Optional<Password> isValid() {
            final var count = password.chars().filter(value -> value == character).count();
            return count >= bounds.getFirst() && count <= bounds.getSecond()
                    ? Optional.of(new Password(password))
                    : Optional.empty();
        }
    }

    private static class Password {
        private final String value;

        public Password(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
