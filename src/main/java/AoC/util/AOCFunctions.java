package AoC.util;

import AoC.day2.Day2;
import io.reactivex.rxjava3.core.Flowable;

import java.util.Optional;
import java.util.function.Function;
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

    public static <A, B, C> Optional<C> map2(Optional<A> a, Optional<B> b, Function<A, Function<B, C>> f) {
        return a.flatMap(ax -> b.map(bx -> f.apply(ax).apply(bx)));
    }

    public static <A, B, C, D, E, F, G, H, I> Optional<I> map8(Optional<A> a, Optional<B> b, Optional<C> c,
                                                               Optional<D> d, Optional<E> e, Optional<F> f,
                                                               Optional<G> g, Optional<H> h,
                                                               Function<A, Function<B, Function<C, Function<D,
                                                                        Function<E, Function<F, Function<G,
                                                                                Function<H, Optional<I>>>>>>>>> func) {
        return a.flatMap(
                ax -> b.flatMap(
                        bx -> c.flatMap(
                                cx -> d.flatMap(
                                        dx -> e.flatMap(
                                                ex -> f.flatMap(
                                                        fx -> g.flatMap(
                                                                gx -> h.flatMap(
                                                                        hx -> func.apply(ax).apply(bx).apply(cx)
                                                                                .apply(dx).apply(ex).apply(fx).apply(gx)
                                                                                .apply(hx)))))))));
    }
}
