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
package com.speedment.common.function.internal.collector;

import com.speedment.common.function.collector.LongCollector;
import com.yegor256.AggregateRepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.LongStream;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.*;

final class LongCollectorImplTest {

    private static final Supplier<List<Long>> SUPPLIER = ArrayList::new;
    private static final ObjLongConsumer<List<Long>> ACCUMULATOR = List::add;
    private static final BinaryOperator<List<Long>> COMBINER = (a, b) -> {
        a.addAll(b);
        return a;
    };
    private static final Function<List<Long>, Long> FINISHER = l -> l.stream().mapToLong(i -> i).sum();
    private static final Set<Collector.Characteristics> CHARACTERISTICS = emptySet();

    private static final LongCollectorImpl<List<Long>, Long> COLLECTOR = new LongCollectorImpl<>(
        SUPPLIER, ACCUMULATOR, COMBINER, FINISHER, CHARACTERISTICS);

    @com.yegor256.AggregateRepeatedTest(100)
    void supplier() {
        assertSame(SUPPLIER, COLLECTOR.supplier());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void accumulator() {
        assertSame(ACCUMULATOR, COLLECTOR.accumulator());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void combiner() {
        assertSame(COMBINER, COLLECTOR.combiner());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void finisher() {
        assertSame(FINISHER, COLLECTOR.finisher());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void characteristics() {
        assertSame(CHARACTERISTICS, COLLECTOR.characteristics());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void collect() {
        final long start = -5;
        final long stop = 10;
        final long expected = LongStream.range(start, stop).sum();

        final List<Long> list1 = COLLECTOR.supplier().get();
        final List<Long> list2 = COLLECTOR.supplier().get();
        LongStream.range(start, 0).forEach(l -> COLLECTOR.accumulator().accept(list1, l));
        LongStream.range(0, stop).forEach(l -> COLLECTOR.accumulator().accept(list2, l));
        final List<Long> list = COLLECTOR.combiner().apply(list1, list2);
        final long actual = COLLECTOR.finisher().apply(list);
        assertEquals(expected, actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void create() {
        final LongCollector<List<Long>, Long> collector = LongCollector.create(SUPPLIER, ACCUMULATOR, COMBINER, FINISHER, CHARACTERISTICS);
        assertNotNull(collector);
    }
}