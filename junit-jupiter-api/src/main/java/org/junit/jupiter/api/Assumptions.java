/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.api;

import static org.apiguardian.api.API.Status.STABLE;
import static org.junit.jupiter.api.AssertionUtils.nullSafeGet;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.platform.commons.util.ExceptionUtils;
import org.junit.platform.commons.util.StringUtils;
import org.junit.platform.commons.util.UnrecoverableExceptions;
import org.opentest4j.TestAbortedException;

/**
 * {@code Assumptions} is a collection of utility methods that support
 * conditional test execution based on <em>assumptions</em>.
 *
 * <p>In direct contrast to failed {@linkplain Assertions assertions},
 * failed assumptions do not result in a test <em>failure</em>; rather,
 * a failed assumption results in a test being <em>aborted</em>.
 *
 * <p>Assumptions are typically used whenever it does not make sense to
 * continue execution of a given test method &mdash; for example, if the
 * test depends on something that does not exist in the current runtime
 * environment.
 *
 * <p>Although it is technically possible to extend this class, extension is
 * strongly discouraged. The JUnit Team highly recommends that the methods
 * defined in this class be used via <em>static imports</em>.
 *
 * @since 5.0
 * @see TestAbortedException
 * @see Assertions
 */
@API(status = STABLE, since = "5.0")
public class Assumptions {

	/**
	 * Protected constructor allowing subclassing but not direct instantiation.
	 *
	 * @since 5.3
	 */
	protected Assumptions() {
		/* no-op */
	}

	// --- assumeTrue ----------------------------------------------------

	/**
	 * Validate the given assumption.
	 *
	 * @param assumption the assumption to validate
	 * @throws TestAbortedException if the assumption is not {@code true}
	 */
	public static void assumeTrue(boolean assumption) throws TestAbortedException {
		assumeTrue(assumption, "assumption is not true");
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @throws TestAbortedException if the assumption is not {@code true}
	 */
	public static void assumeTrue(BooleanSupplier assumptionSupplier) throws TestAbortedException {
		assumeTrue(assumptionSupplier.getAsBoolean(), "assumption is not true");
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @param message the message to be included in the {@code TestAbortedException}
	 * if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code true}
	 */
	public static void assumeTrue(BooleanSupplier assumptionSupplier, String message) throws TestAbortedException {
		assumeTrue(assumptionSupplier.getAsBoolean(), message);
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumption the assumption to validate
	 * @param messageSupplier the supplier of the message to be included in
	 * the {@code TestAbortedException} if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code true}
	 */
	public static void assumeTrue(boolean assumption, Supplier<String> messageSupplier) throws TestAbortedException {
		if (!assumption) {
			throwTestAbortedException(messageSupplier.get());
		}
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumption the assumption to validate
	 * @param message the message to be included in the {@code TestAbortedException}
	 * if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code true}
	 */
	public static void assumeTrue(boolean assumption, String message) throws TestAbortedException {
		if (!assumption) {
			throwTestAbortedException(message);
		}
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @param messageSupplier the supplier of the message to be included in
	 * the {@code TestAbortedException} if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code true}
	 */
	public static void assumeTrue(BooleanSupplier assumptionSupplier, Supplier<String> messageSupplier)
			throws TestAbortedException {

		assumeTrue(assumptionSupplier.getAsBoolean(), messageSupplier);
	}

	// --- assumeFalse ----------------------------------------------------

	/**
	 * Validate the given assumption.
	 *
	 * @param assumption the assumption to validate
	 * @throws TestAbortedException if the assumption is not {@code false}
	 */
	public static void assumeFalse(boolean assumption) throws TestAbortedException {
		assumeFalse(assumption, "assumption is not false");
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @throws TestAbortedException if the assumption is not {@code false}
	 */
	public static void assumeFalse(BooleanSupplier assumptionSupplier) throws TestAbortedException {
		assumeFalse(assumptionSupplier.getAsBoolean(), "assumption is not false");
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @param message the message to be included in the {@code TestAbortedException}
	 * if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code false}
	 */
	public static void assumeFalse(BooleanSupplier assumptionSupplier, String message) throws TestAbortedException {
		assumeFalse(assumptionSupplier.getAsBoolean(), message);
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumption the assumption to validate
	 * @param messageSupplier the supplier of the message to be included in
	 * the {@code TestAbortedException} if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code false}
	 */
	public static void assumeFalse(boolean assumption, Supplier<String> messageSupplier) throws TestAbortedException {
		if (assumption) {
			throwTestAbortedException(messageSupplier.get());
		}
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumption the assumption to validate
	 * @param message the message to be included in the {@code TestAbortedException}
	 * if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code false}
	 */
	public static void assumeFalse(boolean assumption, String message) throws TestAbortedException {
		if (assumption) {
			throwTestAbortedException(message);
		}
	}

	/**
	 * Validate the given assumption.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @param messageSupplier the supplier of the message to be included in
	 * the {@code TestAbortedException} if the assumption is invalid
	 * @throws TestAbortedException if the assumption is not {@code false}
	 */
	public static void assumeFalse(BooleanSupplier assumptionSupplier, Supplier<String> messageSupplier)
			throws TestAbortedException {

		assumeFalse(assumptionSupplier.getAsBoolean(), messageSupplier);
	}

	// --- assumingThat --------------------------------------------------

	/**
	 * Execute the supplied {@link Executable}, but only if the supplied
	 * assumption is valid.
	 *
	 * <p>Unlike the other assumption methods, this method will not abort the test.
	 * If the assumption is invalid, this method does nothing. If the assumption is
	 * valid and the {@code executable} throws an exception, it will be treated like
	 * a regular test <em>failure</em>. That exception will be rethrown <em>as is</em>
	 * but {@link ExceptionUtils#throwAsUncheckedException masked} as an unchecked
	 * exception.
	 *
	 * @param assumptionSupplier the supplier of the assumption to validate
	 * @param executable the block of code to execute if the assumption is valid
	 * @see #assumingThat(boolean, Executable)
	 */
	public static void assumingThat(BooleanSupplier assumptionSupplier, Executable executable) {
		assumingThat(assumptionSupplier.getAsBoolean(), executable);
	}

	/**
	 * Execute the supplied {@link Executable}, but only if the supplied
	 * assumption is valid.
	 *
	 * <p>Unlike the other assumption methods, this method will not abort the test.
	 * If the assumption is invalid, this method does nothing. If the assumption is
	 * valid and the {@code executable} throws an exception, it will be treated like
	 * a regular test <em>failure</em>. That exception will be rethrown <em>as is</em>
	 * but {@link ExceptionUtils#throwAsUncheckedException masked} as an unchecked
	 * exception.
	 *
	 * @param assumption the assumption to validate
	 * @param executable the block of code to execute if the assumption is valid
	 * @see #assumingThat(BooleanSupplier, Executable)
	 */
	public static void assumingThat(boolean assumption, Executable executable) {
		if (assumption) {
			try {
				executable.execute();
			}
			catch (Throwable t) {
				ExceptionUtils.throwAsUncheckedException(t);
			}
		}
	}

	private static void throwTestAbortedException(String message) {
		throw new TestAbortedException(
			StringUtils.isNotBlank(message) ? ("Assumption failed: " + message) : "Assumption failed");
	}

	// --- assumingDoesNotThrow --------------------------------------------------

	// --- executable ---

	/**
	 * Validate that execution of the supplied executable does not throw any kind of exception.
	 *
	 * @param executable the executable which to check whether it throws an exception when executed
	 * @throws TestAbortedException if the execution of the executable throws an exception
	 */
	public static void assumeDoesNotThrow(Executable executable) {
		assumeDoesNotThrow(executable, (Object) null);
	}

	/**
	 * Validate that execution of the supplied executable does not throw any kind of exception.
	 *
	 * @param executable the executable which to check whether it throws an exception when executed
	 * @param message the message to be included in the {@code TestAbortedException}
	 * if the execution throws an exception
	 * @throws TestAbortedException if the execution of the executable throws an exception
	 */
	public static void assumeDoesNotThrow(Executable executable, String message) {
		assumeDoesNotThrow(executable, (Object) message);
	}

	/**
	 * Validate that execution of the supplied executable does not throw any kind of exception.
	 *
	 * @param executable the executable which to check whether it throws an exception when executed
	 * @param messageSupplier the supplier of the message to be included in
	 * the {@code TestAbortedException} if the execution throws an exception
	 * @throws TestAbortedException if the execution of the executable throws an exception
	 */
	public static void assumeDoesNotThrow(Executable executable, Supplier<String> messageSupplier) {
		assumeDoesNotThrow(executable, (Object) messageSupplier);
	}

	/**
	 * Validate that execution of the supplied executable does not throw any kind of exception.
	 *
	 * @param executable the executable which to check whether it throws an exception when executed
	 * @param messageOrSupplier the message itself or the supplier of the message to be included in
	 * the {@code TestAbortedException} if the execution throws an exception
	 * @throws TestAbortedException if the execution of the executable throws an exception
	 */
	public static void assumeDoesNotThrow(Executable executable, Object messageOrSupplier) throws TestAbortedException {
		try {
			executable.execute();
		}
		catch (Throwable t) {
			UnrecoverableExceptions.rethrowIfUnrecoverable(t);
			throwTestAbortedException(nullSafeGet(messageOrSupplier));
		}
	}

	// --- supplier ---

	/**
	 * Validate that execution of the supplied supplier does not throw any kind of exception.
	 *
	 * @param supplier the supplied supplier which to check whether it throws an exception when executed
	 * @return the supplier's result if the assumption passes
	 * @throws TestAbortedException if the execution of the supplied supplier throws an exception
	 */
	public static <T> T assumeDoesNotThrow(ThrowingSupplier<T> supplier) {
		return assumeDoesNotThrow(supplier, (Object) null);
	}

	/**
	 * Validate that execution of the supplied supplier does not throw any kind of exception.
	 *
	 * @param supplier the supplied supplier which to check whether it throws an exception when executed
	 * @param message the message to be included in the {@code TestAbortedException}
	 * if the execution throws an exception
	 * @return the supplier's result if the assumption passes
	 * @throws TestAbortedException if the execution of the supplied supplier throws an exception
	 */
	public static <T> T assumeDoesNotThrow(ThrowingSupplier<T> supplier, String message) {
		return assumeDoesNotThrow(supplier, (Object) message);
	}

	/**
	 * Validate that execution of the supplied supplier does not throw any kind of exception.
	 *
	 * @param supplier the supplied supplier which to check whether it throws an exception when executed
	 * @param messageSupplier the supplier of the message to be included in
	 * the {@code TestAbortedException} if the execution throws an exception
	 * @return the supplier's result if the assumption passes
	 * @throws TestAbortedException if the execution of the supplied supplier throws an exception
	 */
	public static <T> T assumeDoesNotThrow(ThrowingSupplier<T> supplier, Supplier<String> messageSupplier) {
		return assumeDoesNotThrow(supplier, (Object) messageSupplier);
	}

	/**
	 * Validate that execution of the supplied supplier does not throw any kind of exception.
	 *
	 * @param supplier the supplier which to check whether it throws an exception when executed
	 * @param messageOrSupplier the message itself or the supplier of the message to be included in
	 * the {@code TestAbortedException} if the execution throws an exception
	 * @return the supplier's result if the assumption passes
	 * @throws TestAbortedException if the execution of the supplied supplier throws an exception
	 */
	public static <T> T assumeDoesNotThrow(ThrowingSupplier<T> supplier, Object messageOrSupplier)
			throws TestAbortedException {
		try {
			return supplier.get();
		}
		catch (Throwable t) {
			UnrecoverableExceptions.rethrowIfUnrecoverable(t);
			String message = nullSafeGet(messageOrSupplier);
			throw new TestAbortedException(
				StringUtils.isNotBlank(message) ? ("Assumption failed: " + message) : "Assumption failed");

		}
	}
}
