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
package com.speedment.runtime.compute;

import static com.speedment.runtime.compute.expression.ExpressionType.BYTE_NULLABLE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.speedment.runtime.compute.util.Pair;
import org.junit.jupiter.api.Assertions;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;

final class ToByteNullableTest {

    private static final ToByteNullable<String> DEFAULT_NULLABLE = string -> string == null ?
            null : string.getBytes()[0];

    @ParameterizedTest
    @ValueSource(strings = "test")
    void of(String input) {
        final Function<String, Byte> function = string -> string.getBytes()[0];
        final ToByteNullable<String> fromFunction = ToByteNullable.of(function);

        assertNotNull(fromFunction);
        assertEquals(function.apply(input), fromFunction.apply(input));

        final ToByteNullable<String> raw = DEFAULT_NULLABLE;
        final ToByteNullable<String> fromRaw = ToByteNullable.of(raw);

        assertNotNull(fromFunction);
        assertEquals(raw.apply(input), fromRaw.apply(input));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void expressionType() {
        Assertions.assertEquals(BYTE_NULLABLE, DEFAULT_NULLABLE.expressionType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void orThrow() {
        final ToByteNullable<String> nullValue = string -> null;
        assertDoesNotThrow(nullValue::orThrow);

        final ToByte<String> toByte = nullValue.orThrow();
        assertThrows(NullPointerException.class, () -> toByte.applyAsByte(""));

        assertDoesNotThrow(DEFAULT_NULLABLE::orThrow);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "foo", "test"})
    void orElseGet(String input) {
        final ToByteNullable<String> nullValue = string -> null;
        ToByte<String> toByte = nullValue.orElseGet(string -> string.getBytes()[0]);

        assertEquals(input.getBytes()[0], toByte.applyAsByte(input));

        toByte = DEFAULT_NULLABLE.orElseGet(string -> (byte) 0);

        assertEquals(input.getBytes()[0], toByte.applyAsByte(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "foo"})
    void orElse(String input) {
        final ToByteNullable<String> nullValue = string -> null;
        ToByte<String> toByte = nullValue.orElse((byte) 0);

        assertEquals((byte) 0, toByte.applyAsByte(input));

        toByte = DEFAULT_NULLABLE.orElse((byte) 0);

        assertEquals(input.getBytes()[0], toByte.applyAsByte(input));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void abs() {
        assertNotNull(DEFAULT_NULLABLE.abs());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void negate() {
        assertNotNull(DEFAULT_NULLABLE.negate());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sign() {
        assertNotNull(DEFAULT_NULLABLE.sign());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sqrt() {
        assertNotNull(DEFAULT_NULLABLE.sqrt());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToDoubleIfPresent() {
        final ToDoubleNullable<String> toDoubleNullable = DEFAULT_NULLABLE
                .mapToDoubleIfPresent(b -> 1);

        assertNotNull(toDoubleNullable);
        assertEquals(1, toDoubleNullable.applyAsDouble("test"));

        assertNull(toDoubleNullable.apply(null));
        assertEquals(1, toDoubleNullable.apply("test"));

        final ToDouble<String> orElseGet = toDoubleNullable.orElseGet(d -> 0);
        assertEquals(1, orElseGet.applyAsDouble("test"));
        assertEquals(0, orElseGet.applyAsDouble(null));

        final ToDouble<String> orElse = toDoubleNullable.orElse((double) 0);
        assertEquals(1, orElse.applyAsDouble("test"));
        assertEquals(0, orElse.applyAsDouble(null));

        assertTrue(toDoubleNullable.isNotNull("test"));
        assertFalse(toDoubleNullable.isNotNull(null));

        assertTrue(toDoubleNullable.isNull(null));
        assertFalse(toDoubleNullable.isNull("test"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapIfPresent() {
        final ToByteNullable<String> toByteNullable = DEFAULT_NULLABLE.mapIfPresent(b -> b);

        assertNotNull(toByteNullable);
        assertEquals("test".getBytes()[0], toByteNullable.applyAsByte("test"));

        assertNull(toByteNullable.apply(null));
        assertEquals("test".getBytes()[0], toByteNullable.apply("test"));

        final ToByte<String> orElseGet = toByteNullable.orElseGet(c -> (byte) 0);
        assertEquals("test".getBytes()[0], orElseGet.applyAsByte("test"));
        assertEquals(0, orElseGet.applyAsByte(null));

        final ToByte<String> orElse = toByteNullable.orElse((byte) 0);
        assertEquals("test".getBytes()[0], orElse.applyAsByte("test"));
        assertEquals(0, orElse.applyAsByte(null));

        assertTrue(toByteNullable.isNotNull("test"));
        assertFalse(toByteNullable.isNotNull(null));

        assertTrue(toByteNullable.isNull(null));
        assertFalse(toByteNullable.isNull("test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "foo"})
    void hash(String input) {
        final ToByteNullable<String> nullValue = string -> null;
        assertEquals(0, nullValue.hash(input));

        assertNotEquals(0, DEFAULT_NULLABLE.hash(input));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void compare() {
        final ToByteNullable<String> raw = string -> string.length() > 4 ? string.getBytes()[0] : null;

        final Pair<String, String> nullNull = new Pair<>("foo", "bar");
        final Pair<String, String> nullHas = new Pair<>("foo", "longer");
        final Pair<String, String> hasNull = new Pair<>("longer", "foo");
        final Pair<String, String> hasHas = new Pair<>("longer", "longer");

        assertEquals(0, raw.compare(nullNull.getFirst(), nullNull.getSecond()));
        assertEquals(1, raw.compare(nullHas.getFirst(), nullHas.getSecond()));
        assertEquals(-1, raw.compare(hasNull.getFirst(), hasNull.getSecond()));
        assertEquals(0, raw.compare(hasHas.getFirst(), hasHas.getSecond()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void compose() {
        assertThrows(NullPointerException.class, () -> DEFAULT_NULLABLE.compose(null));

        final ToByteNullable<Boolean> composed = DEFAULT_NULLABLE.compose(Object::toString);

        assertNotNull(composed);
    }
}
