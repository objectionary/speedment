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
package com.speedment.runtime.field.comparator;

import com.yegor256.AggregateRepeatedTest;

import static com.speedment.runtime.field.comparator.NullOrder.FIRST;
import static com.speedment.runtime.field.comparator.NullOrder.LAST;
import static com.speedment.runtime.field.comparator.NullOrder.NONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Per Minborg
 */
final class NullOrderTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void testNoneReversed() {
        assertEquals(NONE, NONE.reversed());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFirstReversed() {
        assertEquals(LAST, FIRST.reversed());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testLastReversed() {
        assertEquals(FIRST, LAST.reversed());
    }

}
