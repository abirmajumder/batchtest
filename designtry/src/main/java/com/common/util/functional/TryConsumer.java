package com.common.util.functional;

public interface TryConsumer<T, E extends Throwable> {
	void accept(T t) throws E;
}
