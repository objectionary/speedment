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
package com.speedment.common.tuple;

import com.yegor256.AggregateRepeatedTest;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

final class TuplesTest {
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of0() {
        assertTuple(Tuples.of(), 0);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple0() {
        final Function<Integer, Tuple0> mapper = Tuples.toTuple();
        assertTuple(mapper.apply(0), 0);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of1() {
        assertTuple(Tuples.of(0), 1);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple1() {
        final Function<Integer, Tuple1<Integer>> mapper = Tuples.toTuple(i -> i + 0);
        assertTuple(mapper.apply(0), 1);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of2() {
        assertTuple(Tuples.of(0, 1), 2);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple2() {
        final Function<Integer, Tuple2<Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1);
        assertTuple(mapper.apply(0), 2);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of3() {
        assertTuple(Tuples.of(0, 1, 2), 3);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple3() {
        final Function<Integer, Tuple3<Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2);
        assertTuple(mapper.apply(0), 3);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of4() {
        assertTuple(Tuples.of(0, 1, 2, 3), 4);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple4() {
        final Function<Integer, Tuple4<Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3);
        assertTuple(mapper.apply(0), 4);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of5() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4), 5);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple5() {
        final Function<Integer, Tuple5<Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4);
        assertTuple(mapper.apply(0), 5);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of6() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5), 6);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple6() {
        final Function<Integer, Tuple6<Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5);
        assertTuple(mapper.apply(0), 6);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of7() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6), 7);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple7() {
        final Function<Integer, Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6);
        assertTuple(mapper.apply(0), 7);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of8() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7), 8);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple8() {
        final Function<Integer, Tuple8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7);
        assertTuple(mapper.apply(0), 8);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of9() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8), 9);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple9() {
        final Function<Integer, Tuple9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8);
        assertTuple(mapper.apply(0), 9);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of10() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 10);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple10() {
        final Function<Integer, Tuple10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9);
        assertTuple(mapper.apply(0), 10);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of11() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 11);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple11() {
        final Function<Integer, Tuple11<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10);
        assertTuple(mapper.apply(0), 11);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of12() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 12);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple12() {
        final Function<Integer, Tuple12<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11);
        assertTuple(mapper.apply(0), 12);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of13() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 13);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple13() {
        final Function<Integer, Tuple13<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12);
        assertTuple(mapper.apply(0), 13);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of14() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13), 14);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple14() {
        final Function<Integer, Tuple14<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13);
        assertTuple(mapper.apply(0), 14);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of15() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14), 15);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple15() {
        final Function<Integer, Tuple15<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14);
        assertTuple(mapper.apply(0), 15);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of16() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), 16);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple16() {
        final Function<Integer, Tuple16<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15);
        assertTuple(mapper.apply(0), 16);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of17() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), 17);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple17() {
        final Function<Integer, Tuple17<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16);
        assertTuple(mapper.apply(0), 17);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of18() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17), 18);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple18() {
        final Function<Integer, Tuple18<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16, i -> i + 17);
        assertTuple(mapper.apply(0), 18);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of19() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18), 19);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple19() {
        final Function<Integer, Tuple19<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16, i -> i + 17, i -> i + 18);
        assertTuple(mapper.apply(0), 19);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of20() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19), 20);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple20() {
        final Function<Integer, Tuple20<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16, i -> i + 17, i -> i + 18, i -> i + 19);
        assertTuple(mapper.apply(0), 20);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of21() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20), 21);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple21() {
        final Function<Integer, Tuple21<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16, i -> i + 17, i -> i + 18, i -> i + 19, i -> i + 20);
        assertTuple(mapper.apply(0), 21);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of22() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21), 22);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple22() {
        final Function<Integer, Tuple22<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16, i -> i + 17, i -> i + 18, i -> i + 19, i -> i + 20, i -> i + 21);
        assertTuple(mapper.apply(0), 22);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void of23() {
        assertTuple(Tuples.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22), 23);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void toTuple23() {
        final Function<Integer, Tuple23<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer>> mapper = Tuples.toTuple(i -> i + 0, i -> i + 1, i -> i + 2, i -> i + 3, i -> i + 4, i -> i + 5, i -> i + 6, i -> i + 7, i -> i + 8, i -> i + 9, i -> i + 10, i -> i + 11, i -> i + 12, i -> i + 13, i -> i + 14, i -> i + 15, i -> i + 16, i -> i + 17, i -> i + 18, i -> i + 19, i -> i + 20, i -> i + 21, i -> i + 22);
        assertTuple(mapper.apply(0), 23);
    }
    
    private void assertTuple(Tuple tuple, int degree) {
        assertEquals(degree, tuple.degree());
        for (int i = 0; i < degree; i++){
            assertEquals(i, tuple.get(i));
        }
    }
}