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

import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import javax.management.RuntimeErrorException;

import static org.junit.jupiter.api.Assertions.*;

final class DoubleRangeUtilTest {

    @BeforeEach
    void setUp() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requirePositive() {
        assertEquals(1.0, DoubleRangeUtil.requirePositive(1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requirePositive2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requirePositive(-1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requirePositive3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requirePositive(0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNegative() {
        assertEquals(-1.0, DoubleRangeUtil.requireNegative(-1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNegative2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNegative(1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNegative3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNegative(0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireZero() {
        assertEquals(0.0, DoubleRangeUtil.requireZero(0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireZero2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireZero(1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireZero3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireZero(-1.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonPositive() {
        assertEquals(-1.0, DoubleRangeUtil.requireNonPositive(-1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonPositive2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNonPositive(1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonPositive3() {
        assertEquals(0.0, DoubleRangeUtil.requireNonPositive(0.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonNegative() {
        assertEquals(1.0, DoubleRangeUtil.requireNonNegative(1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonNegative2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNonNegative(-1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonNegative3() {
        assertEquals(0.0, DoubleRangeUtil.requireNonNegative(0.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonZero() {
        assertEquals(1.0, DoubleRangeUtil.requireNonZero(1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonZero2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNonZero(0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNonZero3() {
        assertEquals(-1.0, DoubleRangeUtil.requireNonZero(-1.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals() {
        assertEquals(0.0, DoubleRangeUtil.requireEquals(0.0, 0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireEquals(0.0,-1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireEquals(0.0,1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals4() {
        assertEquals(1.0, DoubleRangeUtil.requireEquals(1.0, 1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals5() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireEquals(1.0,0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals6() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireEquals(1.0,-1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals7() {
        assertEquals(-1.0, DoubleRangeUtil.requireEquals(-1.0, -1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals8() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireEquals(-1.0,0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireEquals9() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireEquals(-1.0,1.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals() {
        assertEquals(0.0, DoubleRangeUtil.requireNotEquals(0.0, -1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals2() {
        assertEquals(0.0, DoubleRangeUtil.requireNotEquals(0.0, 1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNotEquals(0.0,0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals4() {
        assertEquals(1.0, DoubleRangeUtil.requireNotEquals(1.0, 0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals5() {
        assertEquals(1.0, DoubleRangeUtil.requireNotEquals(1.0, -1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals6() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNotEquals(1.0,1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals7() {
        assertEquals(-1.0, DoubleRangeUtil.requireNotEquals(-1.0, 0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals8() {
        assertEquals(-1.0, DoubleRangeUtil.requireNotEquals(-1.0, 1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireNotEquals9() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireNotEquals(-1.0,-1.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRange() {
        assertEquals(0.0, DoubleRangeUtil.requireInRange(0.0, -1.0,1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRange2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireInRange(-1.0,0.0,1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRange3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireInRange(1.0,-1.0,0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRange4() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireInRange(0.0,-1.0,0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRangeClosed() {
        assertEquals(0.0, DoubleRangeUtil.requireInRangeClosed(0.0, -1.0,1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRangeClosed2() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireInRangeClosed(-1.0,0.0,1.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRangeClosed3() {
        assertThrows(IllegalArgumentException.class, () -> DoubleRangeUtil.requireInRangeClosed(1.0,-1.0,0.0));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void requireInRangeClosed4() {
        assertEquals(0.0, DoubleRangeUtil.requireInRangeClosed(0.0, -1.0,0.0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequirePositive() {
        assertEquals(1.0, DoubleRangeUtil.requirePositive(1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequirePositive2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requirePositive(-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequirePositive3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requirePositive(0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNegative() {
        assertEquals(-1.0, DoubleRangeUtil.requireNegative(-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNegative2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNegative(1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNegative3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNegative(0.0, RuntimeException::new));
    }

@com.yegor256.AggregateRepeatedTest(100)
    void testRequireZero() {
        assertEquals(0.0, DoubleRangeUtil.requireZero(0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireZero2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireZero(1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireZero3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireZero(-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonPositive() {
        assertEquals(-1.0, DoubleRangeUtil.requireNonPositive(-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonPositive2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNonPositive(1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonPositive3() {
        assertEquals(0.0, DoubleRangeUtil.requireNonPositive(0.0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonNegative() {
        assertEquals(1.0, DoubleRangeUtil.requireNonNegative(1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonNegative2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNonNegative(-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonNegative3() {
        assertEquals(0.0, DoubleRangeUtil.requireNonNegative(0.0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonZero() {
        assertEquals(1.0, DoubleRangeUtil.requireNonZero(1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonZero2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNonZero(0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireNonZero3() {
        assertEquals(-1.0, DoubleRangeUtil.requireNonZero(-1.0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals() {
        assertEquals(0.0, DoubleRangeUtil.requireEquals(0.0, 0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireEquals(0.0,-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireEquals(0.0,1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals4() {
        assertEquals(1.0, DoubleRangeUtil.requireEquals(1.0, 1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals5() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireEquals(1.0,0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals6() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireEquals(1.0,-1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals7() {
        assertEquals(-1.0, DoubleRangeUtil.requireEquals(-1.0, -1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals8() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireEquals(-1.0,0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireEquals9() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireEquals(-1.0,1.0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals() {
        assertEquals(0.0, DoubleRangeUtil.requireNotEquals(0.0, -1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals2() {
        assertEquals(0.0, DoubleRangeUtil.requireNotEquals(0.0, 1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNotEquals(0.0,0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals4() {
        assertEquals(1.0, DoubleRangeUtil.requireNotEquals(1.0, 0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals5() {
        assertEquals(1.0, DoubleRangeUtil.requireNotEquals(1.0, -1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals6() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNotEquals(1.0,1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals7() {
        assertEquals(-1.0, DoubleRangeUtil.requireNotEquals(-1.0, 0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals8() {
        assertEquals(-1.0, DoubleRangeUtil.requireNotEquals(-1.0, 1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRestRequireNotEquals9() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireNotEquals(-1.0,-1.0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange() {
        assertEquals(0.0, DoubleRangeUtil.requireInRange(0.0, -1.0,1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireInRange(-1.0,0.0,1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireInRange(1.0,-1.0,0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRange4() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireInRange(0.0,-1.0,0.0, RuntimeException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed() {
        assertEquals(0.0, DoubleRangeUtil.requireInRangeClosed(0.0, -1.0,1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed2() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireInRangeClosed(-1.0,0.0,1.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed3() {
        assertThrows(RuntimeException.class, () -> DoubleRangeUtil.requireInRangeClosed(1.0,-1.0,0.0, RuntimeException::new));
    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testRequireInRangeClosed4() {
        assertEquals(0.0, DoubleRangeUtil.requireInRangeClosed(0.0, -1.0,0.0, RuntimeException::new));
    }
}