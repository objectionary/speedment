/*
 *
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.compute.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.speedment.runtime.compute.ToDouble;
import com.speedment.runtime.compute.ToDoubleNullable;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;

final class ToDoubleNullableImplTest {

    private final ToDoubleNullableImpl<String> instance = new ToDoubleNullableImpl<>(
            String::length,
            Objects::isNull
    );

    @com.yegor256.AggregateRepeatedTest(100)
    void inner() {
        assertNotNull(instance.inner());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isNullPredicate() {
        assertNotNull(instance.isNullPredicate());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "foo"})
    void apply(String input) {
        assertNull(instance.apply(null));
        assertEquals(input.length(), instance.apply(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "foo"})
    void applyAsDouble(String input) {
        assertEquals(input.length(), instance.applyAsDouble(input));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void orThrow() {
        assertNotNull(instance.orThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void orElseGet() {
        final ToDouble<String> toDouble = instance.orElseGet(string -> 0);

        assertNotNull(toDouble);
        assertEquals("three".length(), toDouble.applyAsDouble("three"));
        assertEquals(0, toDouble.applyAsDouble(null));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void orElse() {
        final ToDouble<String> toDouble = instance.orElse((double) 0);

        assertNotNull(toDouble);
        assertEquals("three".length(), toDouble.applyAsDouble("three"));
        assertEquals(0, toDouble.applyAsDouble(null));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapIfPresent() {
        final ToDoubleNullable<String> toDoubleNullable = instance.mapIfPresent(d -> 0);

        assertNotNull(toDoubleNullable);
        assertEquals(0, toDoubleNullable.applyAsDouble("1"));
        assertNull(toDoubleNullable.apply(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "foo"})
    void hash(String input) {
        // Double#NEGATIVE_INFINITY doesn't work well with assertEqual
        assertTrue(instance.hash(null) < 0);
        assertNotEquals(0, instance.hash(input));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void compare() {
        assertEquals(0, instance.compare(null, null));
        assertEquals(1, instance.compare(null, "test"));
        assertEquals(-1, instance.compare("test", null));
        assertEquals(0, instance.compare("test", "test"));
        assertEquals(1, instance.compare("test", "a"));
        assertEquals(-1, instance.compare("a", "test"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isNull() {
        assertTrue(instance.isNull(null));
        assertFalse(instance.isNull("test"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isNotNull() {
        assertTrue(instance.isNotNull("test"));
        assertFalse(instance.isNotNull(null));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEquals() {
        final ToDoubleNullable<String> copy = instance;
        assertTrue(instance.equals(copy));
        assertFalse(instance.equals(null));

        final ToDoubleNullable<String> another = new ToDoubleNullableImpl<>(
            instance.inner(),
            instance.isNullPredicate()
        );

        final ToDoubleNullable<String> originalSame = new ToDoubleNullableImpl<>(
            instance.inner(),
            Objects::isNull
        );

        final ToDoubleNullable<String> isNullSame = new ToDoubleNullableImpl<>(
            String::length,
            instance.isNullPredicate()
        );

        assertTrue(instance.equals(another));
        assertFalse(instance.equals(originalSame));
        assertFalse(instance.equals(isNullSame));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testHashCode() {
        assertNotEquals(0, instance.hashCode());
    }
}
