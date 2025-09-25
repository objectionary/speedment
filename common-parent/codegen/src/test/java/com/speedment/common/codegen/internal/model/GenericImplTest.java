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
package com.speedment.common.codegen.internal.model;

import com.speedment.common.codegen.model.Generic;
import com.yegor256.AggregateRepeatedTest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

final class GenericImplTest extends AbstractTest<Generic> {

    private final static String NAME = "A";

    public GenericImplTest() {
        super(GenericImpl::new,
                a -> a.setBoundType(Generic.BoundType.SUPER),
                a -> a.setLowerBound("?"),
                a -> a.add(Integer.class)
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void construct() {
        final GenericImpl generic = new GenericImpl("T");
        assertEquals("T", generic.getLowerBound().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void construct2() {
        final GenericImpl generic = new GenericImpl(int.class, long.class);
        assertEquals(Stream.of(int.class, long.class).collect(toList()), generic.getUpperBounds());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void construct3() {
        final GenericImpl generic = new GenericImpl("T", int.class, long.class);
        assertEquals(Stream.of(int.class, long.class).collect(toList()), generic.getUpperBounds());
        assertEquals("T", generic.getLowerBound().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setLowerBound() {
        final String low = "A";
        instance().setLowerBound(low);
        assertEquals(low, instance().getLowerBound().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getLowerBound() {
        assertFalse(instance().getLowerBound().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setBoundType() {
        instance().setBoundType(Generic.BoundType.SUPER);
        assertEquals(Generic.BoundType.SUPER, instance().getBoundType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getBoundType() {
        assertEquals(Generic.BoundType.EXTENDS, instance().getBoundType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getUpperBounds() {
        assertTrue(instance().getUpperBounds().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asTypeIllegal() {
        assertThrows(UnsupportedOperationException.class, instance()::asType);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asTypeIllegal2() {
        instance().add(Integer.class);
        instance().add(List.class);
        assertThrows(UnsupportedOperationException.class, instance()::asType);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asType() {
        instance().setLowerBound("T");
        instance().add(Integer.class);
        instance().add(List.class);
        final Type type = instance().asType();
        assertTrue(type.getTypeName().contains("T"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asTypeWildcard() {
        instance().setLowerBound("?");
        assertThrows(UnsupportedOperationException.class, instance()::asType);
    }
}