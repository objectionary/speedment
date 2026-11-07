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
package com.speedment.common.singletonstream;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.LongStream;

final class SingletonLongStreamTest {

    private static final Double EPSILON = 1e-9;
    private static long ELEMENT = 1;
    private static long OTHER_ELEMENT = 2;
    private static LongUnaryOperator PLUS_ONE = i -> i + 1;

    private SingletonLongStream instance;
    private LongStream reference;
    private AtomicInteger cnt;

    @BeforeEach
    void setUp() {
        instance = (SingletonLongStream) SingletonLongStream.of(ELEMENT);
        reference = LongStream.of(ELEMENT);
        cnt = new AtomicInteger();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void of() {
        final LongStream ss = SingletonLongStream.of(OTHER_ELEMENT);
        final List<Long> s = ss.boxed().collect(toList());
        assertEquals(singletonList(OTHER_ELEMENT), s);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void filter() {
        setUp();
        assertEqualsApplying(s -> s.filter(i -> i == ELEMENT));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void filter2() {
        setUp();
        assertEqualsApplying(s -> s.filter(i -> i != ELEMENT));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void map() {
        setUp();
        assertEqualsApplying(s -> s.map(PLUS_ONE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToObj() {
        setUp();
        assertEqualsApplying(s -> s.mapToObj(Long::valueOf).mapToLong(i -> i));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToInt() {
        setUp();
        assertEqualsApplying(s -> s.mapToInt(i -> (int) i).mapToLong(l -> l));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToDouble() {
        final double actual = instance.mapToDouble(i -> i).findFirst().orElseThrow(NoSuchElementException::new);
        assertEquals(ELEMENT, actual, EPSILON);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void flatMap() {
        setUp();
        assertEqualsApplying(s -> s.flatMap(i -> LongStream.of(0, i)));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void distinct() {
        setUp();
        assertEqualsApplying(LongStream::distinct);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sorted() {
        setUp();
        assertEqualsApplying(LongStream::sorted);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void peek() {
        setUp();
        instance.peek(i -> cnt.incrementAndGet()).forEach(i -> {});
        assertEquals(1, cnt.get());
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, 1L, 2L})
    void limit(long limit) {
        assertEqualsApplying(s -> s.limit(limit));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void limitNegative() {
        assertThrows(IllegalArgumentException.class, () -> instance.limit(-1));
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, 1L, 2L})
    void skip(long skip) {
        assertEqualsApplying(s -> s.skip(skip));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void skipNegative() {
        assertThrows(IllegalArgumentException.class, () -> instance.skip(-1));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEach() {
        setUp();
        instance.forEach(i -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());

        assertThrows(IllegalStateException.class, () -> instance.forEach(x -> {}));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachOrdered() {
        setUp();
        instance.forEachOrdered(i -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());

        assertThrows(IllegalStateException.class, () -> instance.forEachOrdered(x -> {}));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toArray() {
        setUp();
        assertArrayEquals(reference.toArray(), instance.toArray());

        assertThrows(IllegalStateException.class, () -> instance.toArray());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void reduce() {
        setUp();
        assertEqualsReducing(s -> s.reduce(Long::sum));

        assertThrows(IllegalStateException.class, () -> instance.reduce(Long::sum));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testReduce() {
        setUp();
        assertEqualsReducing(s -> s.reduce(3, Long::sum));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void collect() {
        setUp();
        assertEqualsReducing(s -> s.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        assertThrows(IllegalStateException.class, () -> instance.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sum() {
        setUp();
        assertEqualsReducing(LongStream::sum);

        assertThrows(IllegalStateException.class, () -> instance.sum());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void min() {
        setUp();
        assertEqualsReducing(LongStream::min);

        assertThrows(IllegalStateException.class, () -> instance.min());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void max() {
        setUp();
        assertEqualsReducing(LongStream::max);

        assertThrows(IllegalStateException.class, () -> instance.max());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void count() {
        setUp();
        assertEqualsReducing(LongStream::count);

        assertThrows(IllegalStateException.class, () -> instance.count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void average() {
        setUp();
        assertEqualsReducing(LongStream::average);

        assertThrows(IllegalStateException.class, () -> instance.average());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void summaryStatistics() {
        setUp();
        assertSummaryStatisticsEquals(reference.summaryStatistics(), instance.summaryStatistics());

        assertThrows(IllegalStateException.class, () -> instance.summaryStatistics());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void anyMatch(int value) {
        assertEqualsReducing(s -> s.anyMatch(i -> i == value));

        assertThrows(IllegalStateException.class, () -> instance.anyMatch(i -> i == value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void allMatch(int value) {
        assertEqualsReducing(s -> s.allMatch(i -> i == value));

        assertThrows(IllegalStateException.class, () -> instance.allMatch(i -> i == value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void noneMatch(int value) {
        assertEqualsReducing(s -> s.noneMatch(i -> i == value));

        assertThrows(IllegalStateException.class, () -> instance.noneMatch(i -> i == value));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void findFirst() {
        setUp();
        assertEqualsReducing(LongStream::findFirst);

        assertThrows(IllegalStateException.class, () -> instance.findFirst());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void findAny() {
        setUp();
        assertEqualsReducing(LongStream::findAny);

        assertThrows(IllegalStateException.class, () -> instance.findAny());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asDoubleStream() {
        setUp();
        assertEqualsApplying(s -> s.asDoubleStream().mapToLong(l -> (int) l));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void boxed() {
        setUp();
        assertEqualsApplying(s -> s.boxed().mapToLong(i -> i));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sequential() {
        setUp();
        assertEqualsApplying(LongStream::sequential);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void parallel() {
        setUp();
        assertEqualsApplying(LongStream::parallel);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iterator() {
        setUp();
        assertDoesNotThrow(() -> instance.iterator());
        assertThrows(IllegalStateException.class, () -> instance.iterator());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorIntConsumer() {
        setUp();
        instance.iterator().forEachRemaining((LongConsumer) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorConsumer() {
        setUp();
        instance.iterator().forEachRemaining((Consumer<Long>) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliterator() {
        setUp();
        assertDoesNotThrow(() -> instance.spliterator());
        assertThrows(IllegalStateException.class, () -> instance.spliterator());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorIntConsumer() {
        setUp();
        instance.spliterator().forEachRemaining((LongConsumer) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorConsumer() {
        setUp();
        instance.spliterator().forEachRemaining((Consumer<Long>) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isParallel() {
        setUp();
        assertFalse(instance.isParallel());
        final LongStream newStream = instance.parallel();
        assertTrue(newStream.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mutableParallel() {
        instance.parallel();
        assertTrue(instance.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void unordered() {
        setUp();
        assertEqualsApplying(LongStream::unordered);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mutableOnClose() {
        setUp();
        instance.onClose(cnt::incrementAndGet);
        instance.close();
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void onClose() {
        setUp();
        final LongStream newStream = instance.onClose(cnt::incrementAndGet);
        newStream.close();
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void close() {
        assertDoesNotThrow(instance::close);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void takeWhile() {
        setUp();
        assertEquals(1, instance.takeWhile(i -> ELEMENT == i).count());
        assertEquals(0, instance.takeWhile(i -> OTHER_ELEMENT == i).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void dropWhile() {
        setUp();
        assertEquals(0, instance.dropWhile(i -> ELEMENT == i).count());
        assertEquals(1, instance.dropWhile(i -> OTHER_ELEMENT == i).count());
    }

    private void assertEqualsApplying(UnaryOperator<LongStream> operator) {
        final LongStream expected = operator.apply(reference);
        final LongStream actual = operator.apply(instance);
        assertEquals(expected.isParallel(), actual.isParallel());
        assertEqualsReducing(expected, actual, s -> s.boxed().collect(toList()));
    }

    private <R> void assertEqualsReducing(Function<LongStream, R> reducer) {
        assertEqualsReducing(reference, instance, reducer);
    }

    private <R> void assertEqualsReducing(LongStream referenceToTest, LongStream instanceToTest, Function<LongStream, R> reducer) {
        final R expected = reducer.apply(referenceToTest);
        final R actual = reducer.apply(instanceToTest);
        if (expected instanceof Double && actual instanceof Double) {
            assertEquals((Double) expected, (Double) actual, EPSILON);
        }
        assertEquals(expected, actual);
    }

    private void assertSummaryStatisticsEquals(LongSummaryStatistics expected, LongSummaryStatistics actual) {
        assertEquals(expected.getCount(), actual.getCount());
        assertEquals(expected.getAverage(), actual.getAverage(), EPSILON);
        assertEquals(expected.getAverage(), actual.getAverage(), EPSILON);
    }

}
