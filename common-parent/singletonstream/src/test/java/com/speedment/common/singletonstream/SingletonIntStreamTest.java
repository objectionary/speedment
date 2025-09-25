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
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

final class SingletonIntStreamTest {

    private static final Double EPSILON = 1e-9;
    private static int ELEMENT = 1;
    private static int OTHER_ELEMENT = 2;
    private static IntUnaryOperator PLUS_ONE = i -> i + 1;

    private SingletonIntStream instance;
    private IntStream reference;
    private AtomicInteger cnt;

    @BeforeEach
    void setUp() {
        instance = (SingletonIntStream) SingletonIntStream.of(ELEMENT);
        reference = IntStream.of(ELEMENT);
        cnt = new AtomicInteger();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void of() {
        final IntStream ss = SingletonIntStream.of(OTHER_ELEMENT);
        final List<Integer> s = ss.boxed().collect(toList());
        assertEquals(singletonList(OTHER_ELEMENT), s);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void filter() {
        assertEqualsApplying(s -> s.filter(i -> i == ELEMENT));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void filter2() {
        assertEqualsApplying(s -> s.filter(i -> i != ELEMENT));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void map() {
        assertEqualsApplying(s -> s.map(PLUS_ONE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToObj() {
        assertEqualsApplying(s -> s.mapToObj(Integer::valueOf).mapToInt(i -> i));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToLong() {
        assertEqualsApplying(s -> s.mapToLong(i -> i).mapToInt(l -> (int) l));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToDouble() {
        final double actual = instance.mapToDouble(i -> i).findFirst().orElseThrow(NoSuchElementException::new);
        assertEquals(ELEMENT, actual, EPSILON);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void flatMap() {
        assertEqualsApplying(s -> s.flatMap(i -> IntStream.of(0, i)));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void distinct() {
        assertEqualsApplying(IntStream::distinct);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sorted() {
        assertEqualsApplying(IntStream::sorted);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void peek() {
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
        instance.forEach(i -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());

        assertThrows(IllegalStateException.class, () -> instance.forEach(x -> {}));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachOrdered() {
        instance.forEachOrdered(i -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());

        assertThrows(IllegalStateException.class, () -> instance.forEachOrdered(x -> {}));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toArray() {
        assertArrayEquals(reference.toArray(), instance.toArray());

        assertThrows(IllegalStateException.class, () -> instance.toArray());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void reduce() {
        assertEqualsReducing(s -> s.reduce(Integer::sum));

        assertThrows(IllegalStateException.class, () -> instance.reduce(Integer::sum));

    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testReduce() {
        assertEqualsReducing(s -> s.reduce(3, Integer::sum));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void collect() {
        assertEqualsReducing(s -> s.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        assertThrows(IllegalStateException.class, () -> instance.collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sum() {
        assertEqualsReducing(IntStream::sum);

        assertThrows(IllegalStateException.class, () -> instance.sum());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void min() {
        assertEqualsReducing(IntStream::min);

        assertThrows(IllegalStateException.class, () -> instance.min());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void max() {
        assertEqualsReducing(IntStream::max);

        assertThrows(IllegalStateException.class, () -> instance.max());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void count() {
        assertEqualsReducing(IntStream::count);

        assertThrows(IllegalStateException.class, () -> instance.count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void average() {
        assertEqualsReducing(IntStream::average);

        assertThrows(IllegalStateException.class, () -> instance.average());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void summaryStatistics() {
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
        assertEqualsReducing(IntStream::findFirst);

        assertThrows(IllegalStateException.class, () -> instance.findFirst());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void findAny() {
        assertEqualsReducing(IntStream::findAny);

        assertThrows(IllegalStateException.class, () -> instance.findAny());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asLongStream() {
        assertEqualsApplying(s -> s.asLongStream().mapToInt(l -> (int) l));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asDoubleStream() {
        assertEqualsApplying(s -> s.asDoubleStream().mapToInt(l -> (int) l));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void boxed() {
        assertEqualsApplying(s -> s.boxed().mapToInt(i -> i));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sequential() {
        assertEqualsApplying(IntStream::sequential);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void parallel() {
        assertEqualsApplying(IntStream::parallel);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iterator() {
        assertDoesNotThrow(() -> instance.iterator());
        assertThrows(IllegalStateException.class, () -> instance.iterator());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorIntConsumer() {
        instance.iterator().forEachRemaining((IntConsumer) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorConsumer() {
        instance.iterator().forEachRemaining((Consumer<Integer>) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliterator() {
        assertDoesNotThrow(() -> instance.spliterator());
        assertThrows(IllegalStateException.class, () -> instance.spliterator());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorIntConsumer() {
        instance.spliterator().forEachRemaining((IntConsumer) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorConsumer() {
        instance.spliterator().forEachRemaining((Consumer<Integer>) i -> cnt.incrementAndGet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isParallel() {
        assertFalse(instance.isParallel());
        final IntStream newStream = instance.parallel();
        assertTrue(newStream.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mutableParallel() {
        instance.parallel();
        assertTrue(instance.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void unordered() {
        assertEqualsApplying(IntStream::unordered);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mutableOnClose() {
        instance.onClose(cnt::incrementAndGet);
        instance.close();
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void onClose() {
        final IntStream newStream = instance.onClose(cnt::incrementAndGet);
        newStream.close();
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void close() {
        assertDoesNotThrow(instance::close);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void takeWhile() {
        assertEquals(1, instance.takeWhile(i -> ELEMENT == i).count());
        assertEquals(0, instance.takeWhile(i -> OTHER_ELEMENT == i).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void dropWhile() {
        assertEquals(0, instance.dropWhile(i -> ELEMENT == i).count());
        assertEquals(1, instance.dropWhile(i -> OTHER_ELEMENT == i).count());
    }

    private void assertEqualsApplying(UnaryOperator<IntStream> operator) {
        final IntStream expected = operator.apply(reference);
        final IntStream actual = operator.apply(instance);
        assertEquals(expected.isParallel(), actual.isParallel());
        assertEqualsReducing(expected, actual, s -> s.boxed().collect(toList()));
    }

    private <R> void assertEqualsReducing(Function<IntStream, R> reducer) {
        assertEqualsReducing(reference, instance, reducer);
    }

    private <R> void assertEqualsReducing(IntStream referenceToTest, IntStream instanceToTest, Function<IntStream, R> reducer) {
        final R expected = reducer.apply(referenceToTest);
        final R actual = reducer.apply(instanceToTest);
        if (expected instanceof Double && actual instanceof Double) {
            assertEquals((Double) expected, (Double) actual, EPSILON);
        }
        assertEquals(expected, actual);
    }

    private void assertSummaryStatisticsEquals(IntSummaryStatistics expected, IntSummaryStatistics actual) {
        assertEquals(expected.getCount(), actual.getCount());
        assertEquals(expected.getAverage(), actual.getAverage(), EPSILON);
        assertEquals(expected.getAverage(), actual.getAverage(), EPSILON);
    }

}
