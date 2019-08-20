package com.common.util.functional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Success<T> extends Try<T> {
    private final T value;

    public Success(T value) {
        this.value = value;
    }

    @Override
    public <U> Try<U> flatMap(TryMapFunction<? super T, Try<U>> f) {
        Objects.requireNonNull(f);
        try {
            return f.apply(value);
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    @Override
    public T recover(Function<? super Throwable, T> f) {
        Objects.requireNonNull(f);
        return value;
    }

    @Override
    public Try<T> recoverWith(TryMapFunction<? super Throwable, Try<T>> f) {
        Objects.requireNonNull(f);
        return this;
    }

    @Override
    public T orElse(T value) {
        return this.value;
    }

    @Override
    public Try<T> orElseTry(TrySupplier<T> f) {
        Objects.requireNonNull(f);
        return this;
    }

    @Override
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public T get() throws Throwable {
        return value;
    }

    @Override
    public T getUnchecked() {
        return value;
    }

    @Override
    public <U> Try<U> map(TryMapFunction<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        try {
            return new Success<>(f.apply(value));
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(TryConsumer<T, E> action) throws E {
      action.accept(value);
      return this;
    }

    @Override
    public Try<T> filter(Predicate<T> p) {
        Objects.requireNonNull(p);

        if (p.test(value)) {
            return this;
        } else {
            return Try.failure(new NoSuchElementException("Predicate does not match for " + value));
        }
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.ofNullable(value);
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(TryConsumer<Throwable, E> action) {
      return this;
    }
}