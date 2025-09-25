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
package com.speedment.common.logger;

import com.yegor256.AggregateRepeatedTest;

import static com.speedment.common.logger.Level.*;
import static org.junit.jupiter.api.Assertions.*;

final class LevelTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void defaultLevel() {
        assertEquals(INFO, Level.defaultLevel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isEqualOrLowerThan() {
        assertTrue(INFO.isEqualOrLowerThan(FATAL));
        assertFalse(FATAL.isEqualOrLowerThan(INFO));
        assertTrue(INFO.isEqualOrLowerThan(INFO));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isEqualOrHigherThan() {
        assertFalse(INFO.isEqualOrHigherThan(FATAL));
        assertTrue(FATAL.isEqualOrHigherThan(INFO));
        assertTrue(INFO.isEqualOrHigherThan(INFO));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toText() {
        for (Level level: Level.values()) {
            assertEquals(5, level.toText().length());
        }
    }
}