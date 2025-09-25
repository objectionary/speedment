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
package com.speedment.common.tuple.internal.nonnullable;

import com.yegor256.AggregateRepeatedTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

final class Tuple4ImplTest<T0, T1, T2, T3> extends AbstractTupleImplTest<Tuple4Impl<Integer, Integer, Integer, Integer>> {
    
    Tuple4ImplTest() {
        super(() -> new Tuple4Impl<>(0, 1, 2, 3), 4);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get0Test() {
        assertEquals(0, (int) instance.get0());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get1Test() {
        assertEquals(1, (int) instance.get1());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get2Test() {
        assertEquals(2, (int) instance.get2());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get3Test() {
        assertEquals(3, (int) instance.get3());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get() {
        IntStream.range(0, 4).forEach(i -> assertEquals(i, instance.get(i)));
        assertThrows(IndexOutOfBoundsException.class, () -> instance.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> instance.get(4));
    }
}