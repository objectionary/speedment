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
package com.speedment.common.tuple.internal.nonnullable.mapper;

import com.speedment.common.tuple.Tuples;
import com.yegor256.AggregateRepeatedTest;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

final class Tuple23MapperImplTest {
    
    private final Function<Integer, Integer> m0 = i -> i + 0;
    private final Function<Integer, Integer> m1 = i -> i + 1;
    private final Function<Integer, Integer> m2 = i -> i + 2;
    private final Function<Integer, Integer> m3 = i -> i + 3;
    private final Function<Integer, Integer> m4 = i -> i + 4;
    private final Function<Integer, Integer> m5 = i -> i + 5;
    private final Function<Integer, Integer> m6 = i -> i + 6;
    private final Function<Integer, Integer> m7 = i -> i + 7;
    private final Function<Integer, Integer> m8 = i -> i + 8;
    private final Function<Integer, Integer> m9 = i -> i + 9;
    private final Function<Integer, Integer> m10 = i -> i + 10;
    private final Function<Integer, Integer> m11 = i -> i + 11;
    private final Function<Integer, Integer> m12 = i -> i + 12;
    private final Function<Integer, Integer> m13 = i -> i + 13;
    private final Function<Integer, Integer> m14 = i -> i + 14;
    private final Function<Integer, Integer> m15 = i -> i + 15;
    private final Function<Integer, Integer> m16 = i -> i + 16;
    private final Function<Integer, Integer> m17 = i -> i + 17;
    private final Function<Integer, Integer> m18 = i -> i + 18;
    private final Function<Integer, Integer> m19 = i -> i + 19;
    private final Function<Integer, Integer> m20 = i -> i + 20;
    private final Function<Integer, Integer> m21 = i -> i + 21;
    private final Function<Integer, Integer> m22 = i -> i + 22;
    private final Tuple23MapperImpl<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> instance = new Tuple23MapperImpl<>(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22);
    
    @com.yegor256.AggregateRepeatedTest(100)
    void degree() {
        assertEquals(23, instance.degree());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void apply() {
        assertEquals(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22), instance.apply(0));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get0() {
        assertEquals(m0, instance.get0());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get1() {
        assertEquals(m1, instance.get1());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get2() {
        assertEquals(m2, instance.get2());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get3() {
        assertEquals(m3, instance.get3());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get4() {
        assertEquals(m4, instance.get4());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get5() {
        assertEquals(m5, instance.get5());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get6() {
        assertEquals(m6, instance.get6());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get7() {
        assertEquals(m7, instance.get7());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get8() {
        assertEquals(m8, instance.get8());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get9() {
        assertEquals(m9, instance.get9());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get10() {
        assertEquals(m10, instance.get10());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get11() {
        assertEquals(m11, instance.get11());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get12() {
        assertEquals(m12, instance.get12());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get13() {
        assertEquals(m13, instance.get13());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get14() {
        assertEquals(m14, instance.get14());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get15() {
        assertEquals(m15, instance.get15());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get16() {
        assertEquals(m16, instance.get16());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get17() {
        assertEquals(m17, instance.get17());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get18() {
        assertEquals(m18, instance.get18());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get19() {
        assertEquals(m19, instance.get19());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get20() {
        assertEquals(m20, instance.get20());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get21() {
        assertEquals(m21, instance.get21());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void get22() {
        assertEquals(m22, instance.get22());
    }
}