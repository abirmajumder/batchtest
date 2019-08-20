package com.common.util.functional;

public interface TrySupplier<U> {
	U get() throws Throwable;
}
