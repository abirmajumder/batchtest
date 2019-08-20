package com.common.util.functional;

public interface TryMapFunction<T, R> {
	R apply(T t) throws Throwable;
}
