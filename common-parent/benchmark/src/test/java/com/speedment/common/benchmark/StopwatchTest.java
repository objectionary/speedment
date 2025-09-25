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
package com.speedment.common.benchmark;

import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

final class StopwatchTest {

    private Stopwatch sw;

    @BeforeEach
    void setup() {
        sw = Stopwatch.create();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void elapsed() {
        sw.start();
        shortSleep();
        assertTrue(sw.elapsed(TimeUnit.NANOSECONDS) > 0L);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void elapsedMillis() {
        sw.start();
        shortSleep();
        assertTrue(sw.elapsedMillis() > 0L);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void elapsedNanos() {
        assertEquals(0L, sw.elapsedNanos());
        sw.start();
        shortSleep();
        assertTrue(sw.elapsedNanos() > 0L);
        sw.stop();
        assertTrue(sw.elapsedNanos() > 0L);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isStarted() {
        assertFalse(sw.isStarted());
        sw.start();
        assertTrue(sw.isStarted());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isStopped() {
        assertFalse(sw.isStopped());
        sw.start();
        assertFalse(sw.isStopped());
        sw.stop();
        assertTrue(sw.isStopped());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void start() {
        sw.start();
        assertTrue(sw.isStarted());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void startTwice() {
        sw.start();
        assertThrows(IllegalStateException.class, () -> sw.start());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void stopBeforeStart() {
        assertThrows(IllegalStateException.class, () -> sw.stop());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void stop() {
        assertDoesNotThrow(() -> {
            sw.start();
            sw.stop();
        });
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void stopTwice() {
        sw.start();
        sw.stop();
        assertThrows(IllegalStateException.class, () -> sw.stop());
    }


    @com.yegor256.AggregateRepeatedTest(100)
    void reset() {
        sw.start();
        shortSleep();
        sw.stop();
        sw.reset();
        assertFalse(sw.isStarted());
        assertEquals(0L, sw.elapsedNanos());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void createStarted() {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        assertTrue(stopwatch.isStarted());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void create() {
        final Stopwatch stopwatch = Stopwatch.create();
        assertFalse(stopwatch.isStarted());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringNotStarted() {
        final String actual = sw.toString();
        assertEquals("0.00 ns", actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringStarted() {
        sw.start();
        shortSleep();
        final String actual = sw.toString();
        assertNotEquals("0.00 ns", actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringNano() {
        sw.start();
        sleep(1);
        final String actual = sw.toString();
        assertNotNull(actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringMicro() {
        sw.start();
        sleep(1 * 2_000);
        final String actual = sw.toString();
        assertNotNull(actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringMilli() {
        sw.start();
        sleep(1 * 2_000_000);
        final String actual = sw.toString();
        assertNotNull(actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringSecond() {
        sw.start();
        sleep(1 * 2_000_000_000);
        final String actual = sw.toString();
        assertNotNull(actual);
    }



    private void shortSleep() {
        sleep(10 * 1_000_000);
    }

    private void sleep(long durationNs) {
        final long until = System.nanoTime() + durationNs;
        while (System.nanoTime() < until) {
            /* spin wait */
        }
    }

}