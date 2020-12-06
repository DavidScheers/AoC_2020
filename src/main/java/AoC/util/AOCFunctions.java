package AoC.util;

import AoC.day2.Day2;
import io.reactivex.rxjava3.core.Flowable;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class AOCFunctions {

    public static Flowable<Tuple<Integer, Integer>> pair(Integer anInt, Flowable<Integer> lines) {
        return lines.map(otherInt -> Tuple.of(anInt, otherInt));
    }

    public static Flowable<Tuple3<Integer, Integer, Integer>> tupled3(Integer anInt, Flowable<Integer> others) {
        return others.flatMap(other -> tupled3_(anInt, other, others));
    }

    public static Flowable<Tuple3<Integer, Integer, Integer>> tupled3_(Integer anInt, Integer other,
                                                                 Flowable<Integer> others) {
        return others.map(third -> Tuple3.of(anInt, other, third));
    }

    public static Integer sum(Tuple<Integer, Integer> intTuple) {
        return intTuple.getFirst() + intTuple.getSecond();
    }

    public static Integer sum(Tuple3<Integer, Integer, Integer> intTuple) {
        return intTuple.getFirst() + intTuple.getSecond() + intTuple.getThird();
    }

    public static Integer multiply(Tuple<Integer, Integer> intTuple) {
        return intTuple.getFirst() * intTuple.getSecond();
    }

    public static Integer multiply(Tuple3<Integer, Integer, Integer> intTuple) {
        return intTuple.getFirst() * intTuple.getSecond() * intTuple.getThird();
    }

    public static Predicate<Day2.PasswordPolicy> policy1() {
        return passwordPolicy -> {
            final var count = passwordPolicy.getPassword().chars()
                    .filter(value -> value == passwordPolicy.getCharacter())
                    .count();
            return count >= passwordPolicy.getBounds().getFirst() && count <= passwordPolicy.getBounds().getSecond();
        };
    }

    public static Predicate<Day2.PasswordPolicy> policy2() {
        return passwordPolicy -> {
            final var password = passwordPolicy.getPassword();
            final var firstChar = password.charAt(passwordPolicy.getBounds().getFirst() + 1);
            final var secondChar = password.charAt(passwordPolicy.getBounds().getSecond() + 1);
            return (passwordPolicy.getCharacter() == firstChar && passwordPolicy.getCharacter() != secondChar)
                    || (passwordPolicy.getCharacter() != firstChar && passwordPolicy.getCharacter() == secondChar);
        };
    }
}
