package AoC.util;

import AoC.day2.Day2;
import io.reactivex.rxjava3.core.Flowable;

import java.util.function.BiFunction;

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
}
