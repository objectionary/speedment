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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.common.singletonstream;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @author Per Minborg
 */
final class SingletonStreamTest {

    private static final Double EPSILON = 1e-9;
    private static String ELEMENT = "A";
    private static String OTHER_ELEMENT = "B";

    private SingletonStream<String> instance;

    @BeforeEach
    void setUp() {
        instance = SingletonStream.of(ELEMENT);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSome() {
        final List<Integer> expected = singletonList(0);
        final List<Integer> actual = instance.map(ELEMENT::indexOf).distinct().unordered().collect(toList());
        assertEquals(expected, actual);
    }

    /**
     * Test of of method, of class SingletonStream.
     */
    @com.yegor256.AggregateRepeatedTest(100)
    void testOf() {
        final SingletonStream<String> ss = SingletonStream.of(OTHER_ELEMENT);
        final List<String> s = ss.collect(toList());
        assertEquals(singletonList(OTHER_ELEMENT), s);
    }

    /**
     * Test of ofNullable method, of class SingletonStream.
     */
    @com.yegor256.AggregateRepeatedTest(100)
    void testOfNullableElement() {
        final Stream<String> ss = SingletonStream.ofNullable(OTHER_ELEMENT);
        final List<String> s = ss.collect(toList());
        assertEquals(singletonList(OTHER_ELEMENT), s);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testOfNullableNull() {
        final Stream<String> ss = SingletonStream.ofNullable(null);
        final List<String> s = ss.collect(toList());
        assertEquals(Collections.emptyList(), s);
    }

    /**
     * Test of filter method, of class SingletonStream.
     */
    @com.yegor256.AggregateRepeatedTest(100)
    void testFilter() {
        assertEquals(1L, instance.filter(ELEMENT::equals).count());
        assertEquals(1L, instance.filter(ELEMENT::equals).filter(Objects::nonNull).count());
    }

    /**
     * Test of map method, of class SingletonStream.
     */
    @com.yegor256.AggregateRepeatedTest(100)
    void testMap() {
        final Optional<String> binLen = instance.map(String::length).map(Integer::toBinaryString).findFirst();
        assertEquals(Optional.of("1"), binLen);

        Optional<String> r = SingletonStream.of("C").map(s -> null).map(a -> "Olle").findAny();
        assertEquals(Optional.of("Olle"), r);

    }

    /**
     * Test of mapToInt method, of class SingletonStream.
     */
    @com.yegor256.AggregateRepeatedTest(100)
    void mapToInt() {
        assertEquals(1, instance.mapToInt(String::length).sum());
    }


    @com.yegor256.AggregateRepeatedTest(100)
    void mapToLong() {
        assertEquals(1, instance.mapToLong(String::length).sum());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mapToDouble() {
        assertEquals(1, instance.mapToDouble(String::length).sum(), EPSILON);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void flatMap() {
        assertEquals(1, instance.flatMap(s -> s.chars().boxed()).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void flatMapToInt() {
        assertEquals(1, instance.flatMapToInt(s -> IntStream.range(0, s.length())).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void flatMapToLong() {
        assertEquals(1, instance.flatMapToLong(s -> LongStream.range(0, s.length())).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void flatMapToDouble() {
        assertEquals(2, instance.flatMapToDouble(s -> DoubleStream.of(0, s.length())).count());
    }


    @com.yegor256.AggregateRepeatedTest(100)
    void distinct() {
        assertEquals(1, instance.distinct().count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sorted() {
        assertEquals(1, instance.sorted().count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sortedArg() {
        assertEquals(1, instance.sorted(Comparator.reverseOrder()).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void peek() {
        final AtomicInteger cnt = new AtomicInteger();
        instance.peek(unused -> cnt.getAndIncrement()).forEach(blackHole());
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void limit() {
        assertEquals(1, instance.limit(1).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void limit0() {
        assertEquals(0, instance.limit(0).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void limitNegative() {
        assertThrows(IllegalArgumentException.class, () -> instance.limit(-1).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void skip() {
        assertEquals(0, instance.skip(1).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void skip0() {
        assertEquals(1, instance.skip(0).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void skipNegative() {
        assertThrows(IllegalArgumentException.class, () -> instance.skip(-1).count());
    }


    @com.yegor256.AggregateRepeatedTest(100)
    void forEach() {
        final List<String> strings = new ArrayList<>();
        instance.forEach(strings::add);
        assertEquals(1, strings.size());

        assertThrows(IllegalStateException.class, () -> instance.forEach(strings::add));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEachOrdered() {
        final List<String> strings = new ArrayList<>();
        instance.forEachOrdered(strings::add);
        assertEquals(1, strings.size());

        assertThrows(IllegalStateException.class, () -> instance.forEachOrdered(strings::add));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toArray() {
        assertEquals(1, instance.toArray().length);

        assertThrows(IllegalStateException.class, () -> instance.toArray());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toArrayGenerator() {
        assertEquals(1, instance.toArray(String[]::new).length);

        assertThrows(IllegalStateException.class, () -> instance.toArray(String[]::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void reduce() {
        assertEquals(Optional.of(ELEMENT), instance.reduce((a, b) -> a + b));

        assertThrows(IllegalStateException.class, () -> instance.reduce((a, b) -> a + b));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void reduce2Arg() {
        assertEquals(OTHER_ELEMENT + ELEMENT, instance.reduce(OTHER_ELEMENT, (a, b) -> a + b));

        assertThrows(IllegalStateException.class, () -> instance.reduce(OTHER_ELEMENT, (a, b) -> a + b));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void reduce3Arg() {
        assertEquals(OTHER_ELEMENT + ELEMENT, instance.reduce(OTHER_ELEMENT, (a, b) -> a + b, (a, b) -> a + b));

        assertThrows(IllegalStateException.class, () -> instance.reduce(OTHER_ELEMENT, (a, b) -> a + b, (a, b) -> a + b));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void collect() {
        assertEquals(ELEMENT, instance.collect(Collectors.joining()));

        assertThrows(IllegalStateException.class, () -> instance.collect(Collectors.joining()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void collect3Arg() {
        assertEquals(singletonList(ELEMENT), instance.collect(ArrayList::new, List::add, ArrayList::addAll));

        assertThrows(IllegalStateException.class, () -> instance.collect(ArrayList::new, List::add, ArrayList::addAll));

    }

    @com.yegor256.AggregateRepeatedTest(100)
    void min() {
        assertEquals(Optional.of(ELEMENT), instance.min(Comparator.naturalOrder()));

        assertThrows(IllegalStateException.class, () -> instance.min(Comparator.naturalOrder()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void max() {
        assertEquals(Optional.of(ELEMENT), instance.max(Comparator.naturalOrder()));

        assertThrows(IllegalStateException.class, () -> instance.max(Comparator.naturalOrder()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void count() {
        assertEquals(1, instance.count());

        assertThrows(IllegalStateException.class, () -> instance.count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void anyMatch() {
        assertTrue(instance.anyMatch(ELEMENT::equals));
        assertFalse(SingletonStream.of(OTHER_ELEMENT).anyMatch(ELEMENT::equals));

        assertThrows(IllegalStateException.class, () -> instance.anyMatch(ELEMENT::equals));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void allMatch() {
        assertTrue(instance.allMatch(ELEMENT::equals));
        assertFalse(SingletonStream.of(OTHER_ELEMENT).allMatch(ELEMENT::equals));

        assertThrows(IllegalStateException.class, () -> instance.allMatch(ELEMENT::equals));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void noneMatch() {
        assertFalse(instance.noneMatch(ELEMENT::equals));
        assertTrue(SingletonStream.of(OTHER_ELEMENT).noneMatch(ELEMENT::equals));

        assertThrows(IllegalStateException.class, () -> instance.noneMatch(ELEMENT::equals));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void findFirst() {
        assertEquals(Optional.of(ELEMENT), instance.findFirst());

        assertThrows(IllegalStateException.class, () -> instance.findFirst());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void findAny() {
        assertEquals(Optional.of(ELEMENT), instance.findAny());

        assertThrows(IllegalStateException.class, () -> instance.findAny());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iterator() {
        final AtomicInteger cnt = new AtomicInteger();
        instance.iterator().forEachRemaining(s -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());

        assertThrows(IllegalStateException.class, () -> instance.iterator());

    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorNextAndThenForEachRemaining() {
        final AtomicInteger cnt = new AtomicInteger();
        final Iterator<String> iterator = instance.iterator();
        iterator.next();
        iterator.forEachRemaining(s -> cnt.incrementAndGet());
        assertEquals(0, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorNext() {
        final AtomicInteger cnt = new AtomicInteger();
        final Iterator<String> iterator = instance.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            cnt.incrementAndGet();
        }
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorOutOfRange() {
        final Iterator<String> iterator = instance.iterator();
        String element = iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void iteratorRemove() {
        assertThrows(UnsupportedOperationException.class, () -> instance.iterator().remove());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliterator() {
        final AtomicInteger cnt = new AtomicInteger();
        instance.spliterator().forEachRemaining(s -> cnt.incrementAndGet());
        assertEquals(1, cnt.get());

        assertThrows(IllegalStateException.class, () -> instance.spliterator());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings({"unchecked", "rawtypes"})
    void spliteratorCharacteristicsOfNullElement() {
        final SingletonStream containsNull = SingletonStream.of(null);
        final Spliterator<String> spliterator = containsNull.spliterator();
        assertEquals(0, spliterator.characteristics() & Spliterator.NONNULL);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorCharacteristics() {
        final int c = instance.spliterator().characteristics();
        assertHasFlag(c, Spliterator.DISTINCT);
        assertHasFlag(c, Spliterator.IMMUTABLE);
        assertHasFlag(c, Spliterator.ORDERED);
        assertHasFlag(c, Spliterator.SIZED);
        assertHasFlag(c, Spliterator.SUBSIZED);
        assertHasFlag(c, Spliterator.NONNULL);
    }

    private void assertHasFlag(int c, int flag) {
        assertTrue((c & flag) > 0);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorEstimateSize() {
        final AtomicInteger cnt = new AtomicInteger();
        final Spliterator<String> spliterator = instance.spliterator();
        assertEquals(1, spliterator.estimateSize());
        spliterator.tryAdvance(e -> cnt.incrementAndGet());
        assertEquals(0, spliterator.estimateSize());
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorTrySplit() {
        assertNull(instance.spliterator().trySplit());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void spliteratorTryAdvance() {
        final Spliterator<String> spliterator = instance.spliterator();
        final AtomicInteger cnt = new AtomicInteger();
        assertTrue(spliterator.tryAdvance(e -> cnt.incrementAndGet()));
        assertFalse(spliterator.tryAdvance(e -> cnt.incrementAndGet()));
    }


    @com.yegor256.AggregateRepeatedTest(100)
    void isParallel() {
        assertFalse(instance.isParallel());
        final Stream<String> newStream = instance.parallel();
        assertTrue(newStream.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mutableParallel() {
        instance.parallel();
        assertTrue(instance.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void parallel() {
        final Stream<String> newStream = instance.parallel();
        assertTrue(newStream.isParallel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void sequential() {
        assertSame(instance, instance.sequential());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void unordered() {
        assertSame(instance, instance.unordered());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void mutableOnClose() {
        final AtomicInteger cnt = new AtomicInteger();
        instance.onClose(cnt::incrementAndGet);
        instance.close();
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void onClose() {
        final AtomicInteger cnt = new AtomicInteger();
        final Stream<String> newStream = instance.onClose(cnt::incrementAndGet);
        newStream.close();
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void close() {
        assertDoesNotThrow(instance::close);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void takeWhile() {
        assertEquals(1, instance.takeWhile(ELEMENT::equals).count());
        assertEquals(0, instance.takeWhile(OTHER_ELEMENT::equals).count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void dropWhile() {
        assertEquals(0, instance.dropWhile(ELEMENT::equals).count());
        assertEquals(1, instance.dropWhile(OTHER_ELEMENT::equals).count());
    }

    private <T> Consumer<T> blackHole() {
        return (T t) -> {};
    }

}
