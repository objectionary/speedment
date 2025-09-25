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
package com.speedment.common.invariant;

import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Per Minborg
 */
@Execution(ExecutionMode.CONCURRENT)
final class IntRangeUtilTest {

    private static final IntPredicate IS_POSITIVE = l -> l > 0;
    private static final IntPredicate IS_NEGATIVE = l -> l < 0;
    private static final IntPredicate IS_ZERO = l -> l == 0;

    @com.yegor256.AggregateRepeatedTest(100)
    void requirePositive() {
        testHelper(IS_POSITIVE, IntRangeUtil::requirePositive);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNegative() {
        testHelper(IS_NEGATIVE, IntRangeUtil::requireNegative);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireZero() {
        testHelper(IS_ZERO, IntRangeUtil::requireZero);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonPositive() {
        testHelper(IS_POSITIVE.negate(), IntRangeUtil::requireNonPositive);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonNegative() {
        testHelper(IS_NEGATIVE.negate(), IntRangeUtil::requireNonNegative);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonZero() {
        testHelper(IS_ZERO.negate(), IntRangeUtil::requireNonZero);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals() {
        final int otherVal = 3;
        testHelper(l -> l == otherVal, l -> IntRangeUtil.requireEquals(l, otherVal));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals() {
        final int otherVal = 3;
        testHelper(l -> l != otherVal, l -> IntRangeUtil.requireNotEquals(l, otherVal));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRange() {
        final int first = -1;
        final int lastExclusive = 4;
        testHelper(l -> l >= first && l < lastExclusive, l -> IntRangeUtil.requireInRange(l, first, lastExclusive));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRangeClosed() {
        final int first = -1;
        final int lastInclusive = 4;
        testHelper(l -> l >= first && l <= lastInclusive, l -> IntRangeUtil.requireInRangeClosed(l, first, lastInclusive));
    }

    private void testHelper(IntPredicate predicate, IntUnaryOperator validator) {
        IntStream.range(-257, 257).forEach(l -> {
            if (predicate.test(l)) {
                long expected = validator.applyAsInt(l);
                assertEquals(l, expected);
            } else {
                try {
                    long expected = validator.applyAsInt(l);
                } catch (IllegalArgumentException e) {
                    // Ignore
                }
            }
        });
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequirePositive() {
        assertEquals(1, IntRangeUtil.requirePositive(1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequirePositive2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requirePositive(-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequirePositive3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requirePositive(0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNegative() {
        assertEquals(-1, IntRangeUtil.requireNegative(-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNegative2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNegative(1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNegative3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNegative(0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireZero() {
        assertEquals(0, IntRangeUtil.requireZero(0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireZero2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireZero(1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireZero3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireZero(-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonPositive() {
        assertEquals(-1.0, IntRangeUtil.requireNonPositive(-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonPositive2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNonPositive(1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonPositive3() {
        assertEquals(0.0, IntRangeUtil.requireNonPositive(0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonNegative() {
        assertEquals(1, IntRangeUtil.requireNonNegative(1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonNegative2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNonNegative(-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonNegative3() {
        assertEquals(0.0, IntRangeUtil.requireNonNegative(0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonZero() {
        assertEquals(1, IntRangeUtil.requireNonZero(1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonZero2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNonZero(0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonZero3() {
        assertEquals(-1, IntRangeUtil.requireNonZero(-1, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals() {
        assertEquals(0, IntRangeUtil.requireEquals(0, 0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireEquals(0,-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireEquals(0,1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals4() {
        assertEquals(1.0, IntRangeUtil.requireEquals(1, 1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals5() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireEquals(1,0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals6() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireEquals(1,-1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals7() {
        assertEquals(-1.0, IntRangeUtil.requireEquals(-1, -1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals8() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireEquals(-1,0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals9() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireEquals(-1,1, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals() {
        assertEquals(0.0, IntRangeUtil.requireNotEquals(0, -1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals2() {
        assertEquals(0.0, IntRangeUtil.requireNotEquals(0, 1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNotEquals(0,0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals4() {
        assertEquals(1.0, IntRangeUtil.requireNotEquals(1, 0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals5() {
        assertEquals(1.0, IntRangeUtil.requireNotEquals(1, -1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals6() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNotEquals(1,1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals7() {
        assertEquals(-1.0, IntRangeUtil.requireNotEquals(-1, 0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals8() {
        assertEquals(-1.0, IntRangeUtil.requireNotEquals(-1, 1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals9() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireNotEquals(-1,-1, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange() {
        assertEquals(0.0, IntRangeUtil.requireInRange(0, -1,1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireInRange(-1,0,1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireInRange(1,-1,0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange4() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireInRange(0,-1,0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed() {
        assertEquals(0.0, IntRangeUtil.requireInRangeClosed(0, -1,1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed2() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireInRangeClosed(-1,0,1, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed3() {
        assertThrows(RuntimeException.class, () -> IntRangeUtil.requireInRangeClosed(1,-1,0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed4() {
        assertEquals(0.0, IntRangeUtil.requireInRangeClosed(0, -1,0, RuntimeException::new));
    }
}
