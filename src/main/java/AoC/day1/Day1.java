package AoC.day1;

import AoC.util.Tuple;
import AoC.util.Tuple3;
import io.reactivex.rxjava3.core.Flowable;

public final class Day1 {

    public void run(Flowable<Integer> allInts) {
        final var product = allInts.flatMap(anInt -> pair(anInt, allInts))
                .filter(tuple -> 2020 == sum(tuple))
                .map(this::multiply)
                .firstElement()
                .subscribe(System.out::println);

        final var product3 = allInts.flatMap(anInt -> tupled3(anInt, allInts))
                .filter(tuple3 -> 2020 == sum(tuple3))
                .map(this::multiply)
                .firstElement()
                .subscribe(System.out::println);
    }

    private Flowable<Tuple<Integer, Integer>> pair(Integer anInt, Flowable<Integer> lines) {
        return lines.map(otherInt -> Tuple.of(anInt, otherInt));
    }

    private Flowable<Tuple3<Integer, Integer, Integer>> tupled3(Integer anInt, Flowable<Integer> others) {
        return others.flatMap(other -> tupled3_(anInt, other, others));
    }

    private Flowable<Tuple3<Integer, Integer, Integer>> tupled3_(Integer anInt, Integer other,
                                                                 Flowable<Integer> others) {
        return others.map(third -> Tuple3.of(anInt, other, third));
    }

    private Integer sum(Tuple<Integer, Integer> intTuple) {
        return intTuple.getFirst() + intTuple.getSecond();
    }

    private Integer sum(Tuple3<Integer, Integer, Integer> intTuple) {
        return intTuple.getFirst() + intTuple.getSecond() + intTuple.getThird();
    }

    private Integer multiply(Tuple<Integer, Integer> intTuple) {
        return intTuple.getFirst() * intTuple.getSecond();
    }

    private Integer multiply(Tuple3<Integer, Integer, Integer> intTuple) {
        return intTuple.getFirst() * intTuple.getSecond() * intTuple.getThird();
    }
}
