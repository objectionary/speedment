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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.runtime.compute;

import static com.speedment.runtime.compute.TestUtil.strings;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.speedment.runtime.compute.expression.ExpressionType;
import com.yegor256.AggregateRepeatedTest;

/**
 *
 * @author Per Minborg
 */
final class ToFloatTest extends AbstractToTest<ToFloat<String>> {

    ToFloatTest() {
        super(ExpressionType.FLOAT);
    }

    @Override
    ToFloat<String> create() {
        return s -> (byte) s.length();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testApplyAsInt() {
        strings().forEach(s -> {
            final long actual = mapper.applyAsLong(s);
            final long expected = (long) instance.applyAsFloat(s);
            assertEquals(expected, actual);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testMapToDouble() {
        strings().forEach(s -> {
            final double expected = (double) mapper.applyAsLong(s)+ 1.0;
            final ToDouble<String> toDouble = instance.mapToDouble(l -> l + 1);
            final double actual = toDouble.applyAsDouble(s);
            assertEquals(expected, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testMap() {
        strings().forEach(s -> {
            final double expected = (double) mapper.applyAsLong(s) + 1.0;
            final ToFloat<String> to = instance.map(l -> (byte) (l + 1));
            final double actual = to.applyAsFloat(s);
            assertEquals(expected, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testCompose() {
        strings().forEach(s -> {
            final ToFloatNullable<String> composed = instance.compose(str -> str + "A");
            assertEquals(mapper.applyAsLong(s + "A"), composed.applyAsFloat(s), EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testOf() {
        strings().forEach(s -> {
            final ToFloat<String> created = ToFloat.of(String::length);
            assertEquals(s.length(), created.applyAsFloat(s), EPSILON);

            final ToFloat<String> fromToFloat = ToFloat.of(created);
            assertEquals(s.length(), fromToFloat.applyAsFloat(s), EPSILON);
        });
    }

}
