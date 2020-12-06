package AoC.util;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Tuple<A, B> {

    private final A first;
    private final B second;

    private Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Tuple<A, B> of(A first, B second) {
        Preconditions.checkNotNull(first);
        Preconditions.checkNotNull(second);
        return new Tuple<>(first, second);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(first, tuple.first) &&
                Objects.equals(second, tuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
