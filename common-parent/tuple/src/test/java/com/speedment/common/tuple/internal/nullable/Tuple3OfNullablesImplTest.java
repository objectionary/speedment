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
package com.speedment.common.tuple.internal.nullable;

import com.yegor256.AggregateRepeatedTest;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

final class Tuple3OfNullablesImplTest<T0, T1, T2> extends AbstractTupleImplTest<Tuple3OfNullablesImpl<Integer, Integer, Integer>> {
    
    Tuple3OfNullablesImplTest() {
        super(() -> new Tuple3OfNullablesImpl<>(0, 1, 2), 3);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get0Test() {
        assertEquals(0, (int) instance.get0().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get1Test() {
        assertEquals(1, (int) instance.get1().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get2Test() {
        assertEquals(2, (int) instance.get2().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull0Test() {
        assertEquals(0, (int) instance.getOrNull0());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull1Test() {
        assertEquals(1, (int) instance.getOrNull1());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull2Test() {
        assertEquals(2, (int) instance.getOrNull2());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get() {
        IntStream.range(0, 3).forEach(i -> assertEquals(i, instance.get(i).orElseThrow(NoSuchElementException::new)));
        assertThrows(IndexOutOfBoundsException.class, () -> instance.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> instance.get(3));
    }
}