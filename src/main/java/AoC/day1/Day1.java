package AoC.day1;

import AoC.util.AOCFunctions;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

import static AoC.util.AOCFunctions.*;

public final class Day1 {

    public Maybe<Integer> run1(Flowable<Integer> allInts) {
        return allInts.flatMap(anInt -> pair(anInt, allInts))
                .filter(tuple -> 2020 == sum(tuple))
                .map(AOCFunctions::multiply)
                .firstElement();
    }

    public Maybe<Integer> run2(Flowable<Integer> allInts) {
        return allInts.flatMap(anInt -> tupled3(anInt, allInts))
                .filter(tuple3 -> 2020 == sum(tuple3))
                .map(AOCFunctions::multiply)
                .firstElement();
    }
}
