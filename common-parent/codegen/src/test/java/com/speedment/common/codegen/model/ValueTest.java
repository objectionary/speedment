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
package com.speedment.common.codegen.model;

import com.yegor256.AggregateRepeatedTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class ValueTest {

    private enum EnumExample {A, B}

    @com.yegor256.AggregateRepeatedTest(100)
    void ofArray() {
        assertNotNull(Value.ofArray());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testOfArray() {
        assertNotNull(Value.ofArray(Arrays.asList(Value.ofText("A"))));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofBoolean() {
        assertNotNull(Value.ofBoolean(true));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofEnum() {
        assertNotNull(Value.ofEnum(EnumExample.class, "A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofNull() {
        assertNotNull(Value.ofNull());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofNumber() {
        assertNotNull(Value.ofNumber(1));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofReference() {
        assertNotNull(Value.ofReference("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofText() {
        assertNotNull(Value.ofText("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofAnonymous() {
        assertNotNull(Value.ofAnonymous(int.class));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void ofInvocation() {
        assertNotNull(Value.ofInvocation("add", Value.ofReference("A")));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testOfInvocation() {
        assertNotNull(Value.ofInvocation(List.class, "add", Value.ofReference("A")));
    }
}