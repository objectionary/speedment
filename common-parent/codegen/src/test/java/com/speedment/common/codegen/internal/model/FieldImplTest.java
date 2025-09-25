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

import com.speedment.common.codegen.model.Class;
import com.speedment.common.codegen.model.Field;
import com.speedment.common.codegen.model.Javadoc;
import com.speedment.common.codegen.model.Value;
import com.yegor256.AggregateRepeatedTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

final class FieldImplTest extends AbstractTest<Field> {

    private final static String NAME = "A";

    public FieldImplTest() {
        super(() -> new FieldImpl(NAME, int.class),
                a -> a.setParent(Class.of("A")),
                a -> a.setName("Z"),
                a -> a.set(Long.class),
                a -> a.set(Value.ofNumber(1)),
                a -> a.javadoc(Javadoc.of("A")),
                a -> a.imports(List.class),
                a -> a.annotate(Integer.class),
                Field::public_
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setParent() {
        instance().setParent(Class.of(NAME));
        assertTrue(instance().getParent().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getParent() {
        assertFalse(instance().getParent().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getImports() {
        assertTrue(instance().getImports().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getName() {
        assertEquals(NAME, instance().getName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setName() {
        instance().setName("Z");
        assertNotEquals(NAME, instance().getName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getType() {
        assertEquals(int.class, instance().getType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void set() {
        instance().set(long.class);
        assertEquals(long.class, instance().getType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getModifiers() {
        assertTrue(instance().getModifiers().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSet() {
        instance().set(Javadoc.of("A"));
        assertTrue(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getJavadoc() {
        assertFalse(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSet1() {
        final Value<?> v = Value.ofNumber(1);
        instance().set(v);
        assertEquals(v, instance().getValue().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getValue() {
        assertFalse(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getAnnotations() {
        assertTrue(instance().getAnnotations().isEmpty());
    }
}