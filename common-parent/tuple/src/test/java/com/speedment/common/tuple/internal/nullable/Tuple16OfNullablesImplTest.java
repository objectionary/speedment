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

final class Tuple16OfNullablesImplTest<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> extends AbstractTupleImplTest<Tuple16OfNullablesImpl<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> {
    
    Tuple16OfNullablesImplTest() {
        super(() -> new Tuple16OfNullablesImpl<>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), 16);
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
    void get3Test() {
        assertEquals(3, (int) instance.get3().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get4Test() {
        assertEquals(4, (int) instance.get4().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get5Test() {
        assertEquals(5, (int) instance.get5().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get6Test() {
        assertEquals(6, (int) instance.get6().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get7Test() {
        assertEquals(7, (int) instance.get7().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get8Test() {
        assertEquals(8, (int) instance.get8().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get9Test() {
        assertEquals(9, (int) instance.get9().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get10Test() {
        assertEquals(10, (int) instance.get10().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get11Test() {
        assertEquals(11, (int) instance.get11().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get12Test() {
        assertEquals(12, (int) instance.get12().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get13Test() {
        assertEquals(13, (int) instance.get13().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get14Test() {
        assertEquals(14, (int) instance.get14().orElseThrow(NoSuchElementException::new));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get15Test() {
        assertEquals(15, (int) instance.get15().orElseThrow(NoSuchElementException::new));
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
    void getOrNull3Test() {
        assertEquals(3, (int) instance.getOrNull3());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull4Test() {
        assertEquals(4, (int) instance.getOrNull4());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull5Test() {
        assertEquals(5, (int) instance.getOrNull5());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull6Test() {
        assertEquals(6, (int) instance.getOrNull6());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull7Test() {
        assertEquals(7, (int) instance.getOrNull7());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull8Test() {
        assertEquals(8, (int) instance.getOrNull8());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull9Test() {
        assertEquals(9, (int) instance.getOrNull9());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull10Test() {
        assertEquals(10, (int) instance.getOrNull10());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull11Test() {
        assertEquals(11, (int) instance.getOrNull11());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull12Test() {
        assertEquals(12, (int) instance.getOrNull12());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull13Test() {
        assertEquals(13, (int) instance.getOrNull13());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull14Test() {
        assertEquals(14, (int) instance.getOrNull14());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getOrNull15Test() {
        assertEquals(15, (int) instance.getOrNull15());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get() {
        IntStream.range(0, 16).forEach(i -> assertEquals(i, instance.get(i).orElseThrow(NoSuchElementException::new)));
        assertThrows(IndexOutOfBoundsException.class, () -> instance.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> instance.get(16));
    }
}