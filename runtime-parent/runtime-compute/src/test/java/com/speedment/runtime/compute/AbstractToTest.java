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

import com.speedment.runtime.compute.expression.Expression;
import com.speedment.runtime.compute.expression.ExpressionType;
import com.speedment.runtime.compute.trait.*;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.function.ToLongFunction;

import static com.speedment.runtime.compute.TestUtil.strings;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Per Minborg
 */
@Execution(ExecutionMode.CONCURRENT)
abstract class AbstractToTest<T extends
        Expression<String> & 
        HasAsDouble<String> & 
        HasAsInt<String> & 
        HasAsLong<String> & 
        HasAbs<T> &
        HasSign<ToByte<String>> & 
        HasSqrt<ToDouble<String>> & 
        HasNegate<T> & 
        HasPow<String> & 
        HasPlus<String> &
        HasMinus<String> &
        HasMultiply<String> &
        HasDivide<String> &
        HasHash<String> &
        HasCompare<String>
    > {

    static final double EPSILON = 10e-10;

    final ExpressionType expressionType;
    final ToLongFunction<String> mapper = s -> (long) s.length();

    T instance;

    AbstractToTest(ExpressionType expressionType) {
        this.expressionType = requireNonNull(expressionType);
    }

    abstract T create();

    @BeforeEach
    void setUp() {
        instance = create();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testExpressionType() {
        assertEquals(expressionType, instance.expressionType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testAsDouble() {
        strings().forEach(s -> {
            final double expected = mapper.applyAsLong(s);
            final ToDouble<String> asDouble = instance.asDouble();
            final double actual = asDouble.applyAsDouble(s);
            assertEquals(expected, actual, EPSILON);
            assertEquals(ExpressionType.DOUBLE, asDouble.expressionType());
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testAsInt() {
        strings().forEach(s -> {
            final int expected = (int) mapper.applyAsLong(s);
            final ToInt<String> asInt = instance.asInt();
            final int actual = asInt.applyAsInt(s);
            assertEquals(expected, actual);
            assertEquals(ExpressionType.INT, asInt.expressionType());
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testAsLong() {
        strings().forEach(s -> {
            final long expected = mapper.applyAsLong(s);
            final ToLong<String> asLong = instance.asLong();
            final long actual = asLong.applyAsLong(s);
            assertEquals(expected, actual);
            assertEquals(ExpressionType.LONG, asLong.expressionType());
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testAbs() {
        strings().forEach(s -> {
            final long expected = mapper.applyAsLong(s);
            final T negatedAbs = instance.negate().abs();
            final long actual = negatedAbs.asLong().applyAsLong(s);
            assertEquals(expected, actual);
            assertEquals(expressionType, negatedAbs.expressionType());
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSign() {
        strings().forEach(s -> {

            final ToByte<String> sign = instance.sign();
            final byte actual = sign.applyAsByte(s);
            assertEquals(1, actual);
            assertEquals(ExpressionType.BYTE, sign.expressionType());

            final byte actual2 = sign.applyAsByte("");
            assertEquals(0, actual2);

            final int actual3 = instance.minus(10_000.0).sign().applyAsByte(s);
            assertEquals(-1, actual3);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSqrt() {
        strings().forEach(s -> {
            final double actual = instance.sqrt().asDouble().applyAsDouble(s);
            assertEquals(Math.sqrt(mapper.applyAsLong(s)), actual, EPSILON);
        });

    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPow_int() {
        strings().forEach(s -> {
            final double actual = instance.pow(2).applyAsDouble(s);
            assertEquals(Math.pow(mapper.applyAsLong(s), 2), actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPow_double() {
        strings().forEach(s -> {
            final double actual = instance.pow(2.0).applyAsDouble(s);
            assertEquals(Math.pow(mapper.applyAsLong(s), 2), actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPow_ToInt() {
        strings().forEach(s -> {
            final double actual = instance.pow((ToInt<String>) u -> 2).applyAsDouble(s);
            assertEquals(Math.pow(mapper.applyAsLong(s), 2), actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPow_ToDouble() {
        strings().forEach(s -> {
            final double actual = instance.pow((ToInt<String>) u -> 2).applyAsDouble(s);
            assertEquals(Math.pow(mapper.applyAsLong(s), 2), actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testPlus_byte() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.plus((byte) 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testPlus_ToByte() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.plus((ToByte<String>) u -> (byte) 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testPlus_int() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.plus(1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testPlus_ToInt() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.plus((ToInt<String>) u -> 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testPlus_long() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.plus(1L)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testPlus_ToLong() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.plus((ToLong<String>) u -> 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPlus_double() {
        strings().forEach(s -> {
            final double actual = instance.plus(1.0).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPlus_ToDouble() {
        strings().forEach(s -> {
            final double actual = instance.plus((ToDouble<String>) u -> 1.0).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) + 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMinus_byte() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.minus((byte) 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMinus_ToByte() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.minus((ToByte<String>) u -> (byte) 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMinus_int() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.minus(1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMinus_ToInt() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.minus((ToInt<String>) u -> 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMinus_long() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.minus(1L)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMinus_ToLong() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.minus((ToLong<String>) u -> 1)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testMinus_double() {
        strings().forEach(s -> {
            final double actual = instance.minus(1.0).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testMinus_ToDouble() {
        strings().forEach(s -> {
            final double actual = instance.minus((ToDouble<String>) u -> 1.0).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) - 1.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMultiply_byte() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.multiply((byte) 2)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMultiply_ToByte() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.multiply((ToByte<String>) u -> (byte) 2)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMultiply_int() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.multiply(2)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMultiply_ToInt() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.multiply((ToInt<String>) u -> 2)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMultiply_long() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.multiply(2L)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings("unchecked")
    void testMultiply_ToLong() {
        strings().forEach(s -> {
            final double actual = ((HasAsDouble<String>)instance.multiply((ToLong<String>) u -> 2)).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testMultiply_double() {
        strings().forEach(s -> {
            final double actual = instance.multiply(2.0).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testMultiply_ToDouble() {
        strings().forEach(s -> {
            final double actual = instance.multiply((ToDouble<String>) u -> 2).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) * 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDivide_int() {
        strings().forEach(s -> {
            final double actual = instance.divide(2).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) / 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDivide_ToInt() {
        strings().forEach(s -> {
            final double actual = instance.divide((ToInt<String>) u -> 2).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) / 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDivide_long() {
        strings().forEach(s -> {
            final double actual = instance.divide(2L).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) / 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDivide_ToLong() {
        strings().forEach(s -> {
            final double actual = instance.divide((ToLong<String>) u -> 2).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) / 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDivide_double() {
        strings().forEach(s -> {
            final double actual = instance.divide(2.0).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) / 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDivide_ToDouble() {
        strings().forEach(s -> {
            final double actual = instance.divide((ToDouble<String>) u -> 2).asDouble().applyAsDouble(s);
            assertEquals(mapper.applyAsLong(s) / 2.0, actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testNegate() {
        strings().forEach(s -> {

            final double actual = instance.negate().asDouble().applyAsDouble(s);
            assertEquals(-mapper.applyAsLong(s), actual, EPSILON);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testHash() {
        strings().forEach(s -> {
            final long actual = instance.hash(s);
            final long NOT_expected = instance.hash(s + "A");
            assertNotEquals(0, actual);
            assertNotEquals(NOT_expected, actual);
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testCompare() {
        final int cmpAb = instance.compare("SHORT", "VERY LONG STRING");
        assertTrue(cmpAb < 0);

        final int cmpBa = instance.compare("VERY LONG STRING", "SHORT");
        assertTrue(cmpBa > 0);

        strings().forEach(s -> {
            final int cmp = instance.compare(s, s);
            assertEquals(0, cmp);
        });
    }

}
