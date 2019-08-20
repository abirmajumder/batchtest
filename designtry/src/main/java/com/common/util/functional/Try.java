package com.common.util.functional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class Try<T> {
    protected Try() {
    }

    public static <U> Try<U> ofFailable(TrySupplier<U> f) {
        Objects.requireNonNull(f);

        try {
            return Try.successful(f.get());
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

    /**
     * Transform success or pass on failure.
     * Takes an optional type parameter of the new type.
     * You need to be specific about the new type if changing type
     *
     * Try.ofFailable(() -&gt; "1").&lt;Integer&gt;map((x) -&gt; Integer.valueOf(x))
     *
     * @param f   function to apply to successful value.
     * @param <U> new type (optional)
     * @return Success&lt;U&gt; or Failure&lt;U&gt;
     */

    public abstract <U> Try<U> map(TryMapFunction<? super T, ? extends U> f);

    /**
     * Transform success or pass on failure, taking a Try&lt;U&gt; as the result.
     * Takes an optional type parameter of the new type.
     * You need to be specific about the new type if changing type.
     *
     * Try.ofFailable(() -&gt; "1").&lt;Integer&gt;flatMap((x) -&gt; Try.ofFailable(() -&gt; Integer.valueOf(x)))
     * returns Integer(1)
     *
     * @param f   function to apply to successful value.
     * @param <U> new type (optional)
     * @return new composed Try
     */
    public abstract <U> Try<U> flatMap(TryMapFunction<? super T, Try<U>> f);

    /**
     * Specifies a result to use in case of failure.
     * Gives access to the exception which can be pattern matched on.
     *
     * Try.ofFailable(() -&gt; "not a number")
     * .&lt;Integer&gt;flatMap((x) -&gt; Try.ofFailable(() -&gt;Integer.valueOf(x)))
     * .recover((t) -&gt; 1)
     * returns Integer(1)
     *
     * @param f function to execute on successful result.
     * @return new composed Try
     */

    public abstract T recover(Function<? super Throwable, T> f);

    /**
     * Try applying f(t) on the case of failure.
     * @param f function that takes throwable and returns result
     * @return a new Try in the case of failure, or the current Success.
     */
    public abstract Try<T> recoverWith(TryMapFunction<? super Throwable, Try<T>> f);

    /**
     * Return a value in the case of a failure.
     * This is similar to recover but does not expose the exception type.
     *
     * @param value return the try's value or else the value specified.
     * @return new composed Try
     */
    public abstract T orElse(T value);

    /**
     * Return another try in the case of failure.
     * Like recoverWith but without exposing the exception.
     *
     * @param f return the value or the value from the new try.
     * @return new composed Try
     */
    public abstract Try<T> orElseTry(TrySupplier<T> f);

    /**
     * Gets the value T on Success or throws the cause of the failure.
     *
     * @return T
     * @throws Throwable produced by the supplier function argument
     */

    public abstract <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * Gets the value T on Success or throws the cause of the failure.
     *
     * @return T
     * @throws Throwable
     */
    public abstract T get() throws Throwable;

    /**
     * Gets the value T on Success or throws the cause of the failure wrapped into a RuntimeException
     * @return T
     * @throws RuntimeException
     */
    public abstract T getUnchecked();

    public abstract boolean isSuccess();

    /**
     * Performs the provided action, when successful
     * @param action action to run
     * @return new composed Try
     * @throws E if the action throws an exception
     */
    public abstract <E extends Throwable> Try<T> onSuccess(TryConsumer<T, E> action) throws E;

    /**
     * Performs the provided action, when failed
     * @param action action to run
     * @return new composed Try
     * @throws E if the action throws an exception
     */
    public abstract <E extends Throwable> Try<T> onFailure(TryConsumer<Throwable, E> action) throws E;

    /**
     * If a Try is a Success and the predicate holds true, the Success is passed further.
     * Otherwise (Failure or predicate doesn't hold), pass Failure.
     * @param pred predicate applied to the value held by Try
     * @return For Success, the same success if predicate holds true, otherwise Failure
     */
    public abstract Try<T> filter(Predicate<T> pred);

    /**
     * Try contents wrapped in Optional.
     * @return Optional of T, if Success, Empty if Failure or null value
     */
    public abstract Optional<T> toOptional();

    /**
     * Factory method for failure.
     *
     * @param e throwable to create the failed Try with
     * @param <U> Type
     * @return a new Failure
     */

    public static <U> Try<U> failure(Throwable e) {
        return new Failure<>(e);
    }

    /**
     * Factory method for success.
     *
     * @param x value to create the successful Try with
     * @param <U> Type
     * @return a new Success
     */
    public static <U> Try<U> successful(U x) {
        return new Success<>(x);
    }
}
