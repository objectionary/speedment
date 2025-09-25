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
package com.speedment.common.tuple.internal;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.internal.nonnullable.Tuple2Impl;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Per Minborg
 */
final class Tuple2ImplTest {

    private static final Integer ONE = 1;
    private static final String TWO = "two";
    private Tuple2<Integer, String> t2;

    @BeforeEach
    void setup() {
        t2 = new Tuple2Impl<>(ONE, TWO);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGet0() {
        assertEquals(ONE, t2.get0());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGet1() {
        assertEquals(TWO, t2.get1());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGetIndex0() {
        assertEquals(ONE, t2.get(0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGetIndex1() {
        assertEquals(TWO, t2.get(1));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testLength() {
        assertEquals(2, t2.degree());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testStream() {
        final List<Object> expected = Arrays.asList(ONE, TWO);
        final List<Object> actual = t2.stream().collect(toList());
        assertEquals(expected, actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testStreamOf() {
        final List<String> expected = Arrays.asList(TWO);
        final List<String> actual = t2.streamOf(String.class).collect(toList());
        assertEquals(expected, actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEquals() {
        final Tuple2<Integer, String> expected = new Tuple2Impl<>(ONE, TWO);
        assertEquals(expected, t2);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testHashcode() {
        final Tuple2<Integer, String> expected = new Tuple2Impl<>(ONE, TWO);
        assertEquals(expected.hashCode(), t2.hashCode());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testToString() {
        final String toString = t2.toString();
        System.out.println(toString);
        assertTrue(toString.contains(ONE.toString()));
        assertTrue(toString.contains(TWO));
    }

}
