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
package com.speedment.common.function;

import com.yegor256.AggregateRepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

final class CharUnaryOperatorTest {

    private final CharUnaryOperator TO_UPPERCASE = Character::toUpperCase;
    private final CharUnaryOperator TO_LOWERCASE = Character::toLowerCase;

    @com.yegor256.AggregateRepeatedTest(100)
    void applyAsChar() {
        assertEquals('A', TO_UPPERCASE.applyAsChar('a'));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void compose() {
        assertEquals('a', TO_LOWERCASE.compose(TO_UPPERCASE).applyAsChar('a'));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void andThen() {
        assertEquals('A', TO_LOWERCASE.andThen(TO_UPPERCASE).applyAsChar('a'));
    }
}