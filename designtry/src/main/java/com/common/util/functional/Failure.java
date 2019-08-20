package com.common.util.functional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Failure<T> extends Try<T> {
    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public <U> Try<U> map(TryMapFunction<? super T, ? extends U> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public <U> Try<U> flatMap(TryMapFunction<? super T, Try<U>> f) {
        Objects.requireNonNull(f);
        return Try.failure(e);
    }

    @Override
    public T recover(Function<? super Throwable, T> f) {
        Objects.requireNonNull(f);
        return f.apply(e);
    }

    @Override
    public Try<T> recoverWith(TryMapFunction<? super Throwable, Try<T>> f) {
        Objects.requireNonNull(f);
        try{
            return f.apply(e);
        }catch(Throwable t){
            return Try.failure(t);
        }
    }

    @Override
    public T orElse(T value) {
        return value;
    }

    @Override
    public Try<T> orElseTry(TrySupplier<T> f) {
        Objects.requireNonNull(f);
        return Try.ofFailable(f);
    }

    @Override
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public T get() throws Throwable {
        throw e;
    }

    @Override
    public T getUnchecked() {
        throw new RuntimeException(e);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(TryConsumer<T, E> action) {
      return this;
    }

    @Override
    public Try<T> filter(Predicate<T> pred) {
        return this;
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(TryConsumer<Throwable, E> action) throws E {
      action.accept(e);
      return this;
    }
}