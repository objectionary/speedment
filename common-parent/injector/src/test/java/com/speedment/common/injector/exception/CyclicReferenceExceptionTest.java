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
package com.speedment.common.injector.exception;

import com.yegor256.AggregateRepeatedTest;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

final class CyclicReferenceExceptionTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void construct1() {
        testGetMessage(new CyclicReferenceException(singleton(Integer.class)));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void construct2() {
        testGetMessage(new CyclicReferenceException(Integer.class));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void construct3() {
        final CyclicReferenceException e0 = new CyclicReferenceException(Long.class);
        testGetMessage(new CyclicReferenceException(Integer.class, e0));
    }

    void testGetMessage(CyclicReferenceException ex) {
        assertTrue(ex.getMessage().contains("Cyclic"));
    }
}