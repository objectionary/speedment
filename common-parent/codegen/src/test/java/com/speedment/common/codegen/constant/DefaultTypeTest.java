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
package com.speedment.common.codegen.constant;

import com.yegor256.AggregateRepeatedTest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

final class DefaultTypeTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void genericType() {
        final ParameterizedType genericType = (ParameterizedType) DefaultType.genericType(List.class, String.class);
        assertEquals(List.class.getName(), genericType.getRawType().getTypeName());
        assertArrayEquals(new Type[]{String.class}, genericType.getActualTypeArguments());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGenericType() {
        final ParameterizedType genericType = (ParameterizedType) DefaultType.genericType(List.class, String.class.getName());
        assertEquals(List.class.getName(), genericType.getRawType().getTypeName());
        assertArrayEquals(new String[]{String.class.getName()}, Stream.of(genericType.getActualTypeArguments()).map(Type::getTypeName).toArray());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void classOf() {
        assertSingleParameter(Class.class, Integer.class, DefaultType::classOf);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void list() {
        assertSingleParameter(List.class, Integer.class, DefaultType::list);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void set() {
        assertSingleParameter(Set.class, Integer.class, DefaultType::set);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void map() {
        assertDoubleParameters(Map.class, Integer.class, Long.class,  DefaultType::map);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void queue() {
        assertSingleParameter(Queue.class, Integer.class, DefaultType::queue);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void stack() {
        assertSingleParameter(Stack.class, Integer.class, DefaultType::stack);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void optional() {
        assertSingleParameter(Optional.class, Integer.class, DefaultType::optional);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void entry() {
        assertDoubleParameters(Map.Entry.class, Integer.class, Long.class,  DefaultType::entry);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void function() {
        assertDoubleParameters(Function.class, Integer.class, Long.class,  DefaultType::function);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void bifunction() {
        assertTripleParameters(BiFunction.class, Integer.class, Long.class, Float.class,  DefaultType::bifunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void intFunction() {
        assertSingleParameter(IntFunction.class, Long.class, DefaultType::intFunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void longFunction() {
        assertSingleParameter(LongFunction.class, Integer.class, DefaultType::longFunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void doubleFunction() {
        assertSingleParameter(DoubleFunction.class, Integer.class, DefaultType::doubleFunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toIntFunction() {
        assertSingleParameter(ToIntFunction.class, Long.class, DefaultType::toIntFunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toLongFunction() {
        assertSingleParameter(ToLongFunction.class, Integer.class, DefaultType::toLongFunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toDoubleFunction() {
        assertSingleParameter(ToDoubleFunction.class, Integer.class, DefaultType::toDoubleFunction);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void unaryOperator() {
        assertSingleParameter(UnaryOperator.class, Integer.class, DefaultType::unaryOperator);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void binaryOperator() {
        assertSingleParameter(BinaryOperator.class, Integer.class, DefaultType::binaryOperator);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void predicate() {
        assertSingleParameter(Predicate.class, Integer.class, DefaultType::predicate);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void bipredicate() {
        assertDoubleParameters(BiPredicate.class, Integer.class, Long.class,  DefaultType::bipredicate);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void consumer() {
        assertSingleParameter(Consumer.class, Integer.class, DefaultType::consumer);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void biconsumer() {
        assertDoubleParameters(BiConsumer.class, Integer.class, Long.class,  DefaultType::biconsumer);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void supplier() {
        assertSingleParameter(Supplier.class, Integer.class, DefaultType::supplier);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void stream() {
        assertSingleParameter(Stream.class, Integer.class, DefaultType::stream);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isPrimitive() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isWrapper() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void wrapperFor() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void primitiveTypes() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void wrapperTypes() {
    }

    private <B, T> void assertSingleParameter(Class<B> rawClass, Class<T> clazz, Function<Class<T>, Type> extractor) {
        final ParameterizedType genericType = (ParameterizedType) extractor.apply(clazz);
        assertEquals(rawClass.getName(), genericType.getRawType().getTypeName());
        assertEquals(1, genericType.getActualTypeArguments().length);
        assertEquals(clazz.getName(), genericType.getActualTypeArguments()[0].getTypeName());
    }

    private <B, K, V> void assertDoubleParameters(Class<B> rawClass, Class<K> keyClass, Class<V> valueClass, BiFunction<Class<K>, Class<V>, Type> extractor) {
        final ParameterizedType genericType = (ParameterizedType) extractor.apply(keyClass, valueClass);
        assertEquals(rawClass.getName(), genericType.getRawType().getTypeName());
        assertEquals(2, genericType.getActualTypeArguments().length);
        assertEquals(keyClass.getName(), genericType.getActualTypeArguments()[0].getTypeName());
        assertEquals(valueClass.getName(), genericType.getActualTypeArguments()[1].getTypeName());
    }

    private <B, K, V, W> void assertTripleParameters(Class<B> rawClass, Class<K> keyClass, Class<V> valueClass, Class<W> triClass, TriFunction<Class<K>, Class<V>, Class<W>, Type> extractor) {
        final ParameterizedType genericType = (ParameterizedType) extractor.apply(keyClass, valueClass, triClass);
        assertEquals(rawClass.getName(), genericType.getRawType().getTypeName());
        assertEquals(3, genericType.getActualTypeArguments().length);
        assertEquals(keyClass.getName(), genericType.getActualTypeArguments()[0].getTypeName());
        assertEquals(valueClass.getName(), genericType.getActualTypeArguments()[1].getTypeName());
        assertEquals(triClass.getName(), genericType.getActualTypeArguments()[2].getTypeName());
    }

    @FunctionalInterface
    interface TriFunction<T0, T1, T2, R> {
        R apply(T0 t0, T1 t1, T2 t2);
    }

}