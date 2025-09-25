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
package com.speedment.common.singletonstream.internal;

import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

import static org.junit.jupiter.api.Assertions.*;

final class SingletonPrimitiveSpliteratorOfLongTest {

    private static final long ELEMENT = 1;
    private SingletonPrimitiveSpliteratorOfLong instance;
    private AtomicInteger cnt;

    @BeforeEach
    void setup() {
        instance = new SingletonPrimitiveSpliteratorOfLong(ELEMENT);
        cnt = new AtomicInteger();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void trySplit() {
        assertNull(instance.trySplit());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tryAdvanceIntConsumer() {
        assertTrue(instance.tryAdvance((LongConsumer) i -> cnt.incrementAndGet()));
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tryAdvanceConsumer() {
        assertTrue(instance.tryAdvance((Consumer<Long>) i -> cnt.incrementAndGet()));
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tryAdvanceIntConsumerAfterAdvance() {
        instance.tryAdvance((LongConsumer) i -> {});
        assertFalse(instance.tryAdvance((LongConsumer) i -> cnt.incrementAndGet()));
        assertEquals(0, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tryAdvanceConsumerAfterAdvance() {
        instance.tryAdvance((Consumer<Long>) i -> {});
        assertFalse(instance.tryAdvance((Consumer<Long>) i -> cnt.incrementAndGet()));
        assertEquals(0, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachRemainingIntConsumer() {
        instance.forEachRemaining((LongConsumer) i -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachRemainingConsumer() {
        instance.forEachRemaining((Consumer<Long>) i -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachRemainingIntConsumerAfterAdvance() {
        instance.tryAdvance((Consumer<Long>) i -> {});
        instance.forEachRemaining((LongConsumer) i -> cnt.incrementAndGet());
        assertEquals(0, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachRemainingConsumerAfterAdvance() {
        instance.tryAdvance((Consumer<Long>) i -> {});
        instance.forEachRemaining((Consumer<Long>) i -> cnt.incrementAndGet());
        assertEquals(0, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void estimateSize() {
        assertEquals(1L, instance.estimateSize());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void estimateSizeAfterAdvance() {
        instance.tryAdvance((Consumer<Long>) i -> {});
        assertEquals(0L, instance.estimateSize());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void characteristics() {
        assertNotEquals(0, instance.characteristics() & Spliterator.NONNULL);
        assertNotEquals(0, instance.characteristics() & Spliterator.SIZED);
        assertNotEquals(0, instance.characteristics() & Spliterator.SUBSIZED);
        assertNotEquals(0, instance.characteristics() & Spliterator.IMMUTABLE);
        assertNotEquals(0, instance.characteristics() & Spliterator.DISTINCT);
        assertNotEquals(0, instance.characteristics() & Spliterator.ORDERED);
    }

}