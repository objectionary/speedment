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

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

import static org.junit.jupiter.api.Assertions.*;

final class SingletonPrimitiveIteratorOfLongTest {

    private static final long ELEMENT = 1;
    private SingletonPrimitiveIteratorOfLong instance;
    private AtomicInteger cnt;

    @BeforeEach
    void setup() {
        instance = new SingletonPrimitiveIteratorOfLong(ELEMENT);
        cnt = new AtomicInteger();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void hasNext() {
        assertTrue(instance.hasNext());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void hasNextAfterConsumed() {
        instance.next();
        assertFalse(instance.hasNext());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void nextInt() {
        assertEquals(ELEMENT, instance.nextLong());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void nextIntAfterNext() {
        instance.next();
        assertThrows(NoSuchElementException.class, instance::nextLong);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void next() {
        assertEquals(ELEMENT, instance.next());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void nextAfterNext() {
        instance.next();
        assertThrows(NoSuchElementException.class, instance::next);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void remove() {
        assertThrows(UnsupportedOperationException.class, instance::remove);
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
    void forEachRemainingIntConsumerAfterNext() {
        instance.next();
        instance.forEachRemaining((LongConsumer)i -> cnt.incrementAndGet());
        assertEquals(0, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachRemainingConsumerAfterNext() {
        instance.next();
        instance.forEachRemaining((Consumer<Long>) i -> cnt.incrementAndGet());
        assertEquals(0, cnt.get());
    }

}