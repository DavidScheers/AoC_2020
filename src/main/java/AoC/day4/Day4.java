package AoC.day4;

import AoC.util.BufferUntil;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

public final class Day4 {

    public Single<Long> run1(Flowable<String> allLines) {
        return bufferUntil(allLines)
                .map(strings -> strings.stream()
                        .map(this::splitLines)
                        .flatMap(Collection::stream)
                        .filter(s -> !s.isEmpty())
                        .collect(toUnmodifiableList()))
                .map(this::parse)
                .filter(Optional::isPresent)
                .count();
    }

    private Optional<Passport> parse(List<String> input) {
        return PassportParser.parse(input);
    }

    private List<String> splitLines(String line) {
        return Arrays.stream(line.split("\\s")).collect(toUnmodifiableList());
    }

    private Flowable<List<String>> bufferUntil(Flowable<String> allLines) {
        return allLines
                .lift(new BufferUntil<>(String::isBlank));
    }


}
