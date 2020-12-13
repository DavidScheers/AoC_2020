package AoC.util;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableOperator;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Function;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

public final class BufferUntil<T> implements FlowableOperator<List<T>, T> {

    final Function<T, Boolean> boundaryPredicate;

    public BufferUntil(Function<T, Boolean> boundaryPredicate) {
        this.boundaryPredicate = boundaryPredicate;
    }

    @Override
    public @NonNull Subscriber<? super T> apply(@NonNull Subscriber<? super List<T>> subscriber) {
        return new BufferWhileSubscriber(subscriber);
    }

    final class BufferWhileSubscriber implements FlowableSubscriber<T> {
        final Subscriber<? super List<T>> actual;

        List<T> buffer = new ArrayList<>();

        public BufferWhileSubscriber(Subscriber<? super List<T>> actual) {
            this.actual = actual;
        }

        @Override
        public void onNext(T t) {
            buffer.add(t);
            try {
                if (boundaryPredicate.apply(t)) {
                    actual.onNext(buffer);
                    buffer = new ArrayList<>();
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }

        @Override
        public void onError(Throwable e) {
            buffer = null;
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            List<T> b = buffer;
            buffer = null;
            if (!b.isEmpty()) {
                actual.onNext(b);
            }
            actual.onComplete();
        }

        @Override
        public void onSubscribe(@NonNull Subscription s) {
           actual.onSubscribe(s);
        }
    }
}
