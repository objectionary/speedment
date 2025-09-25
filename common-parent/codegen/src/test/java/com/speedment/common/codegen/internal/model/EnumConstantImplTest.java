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

import com.speedment.common.codegen.model.*;
import com.speedment.common.codegen.model.Class;
import com.speedment.common.codegen.model.Enum;
import com.yegor256.AggregateRepeatedTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class EnumConstantImplTest extends AbstractTest<EnumConstant> {

    private static final String NAME = "A";
    private static final String OTHER_NAME = "B";

    public EnumConstantImplTest() {
        super(() -> new EnumConstantImpl(NAME),
                a -> a.setParent(Enum.of("A")),
                a -> a.setName("Z"),
                a -> a.javadoc(Javadoc.of("A")),
                a -> a.add(Class.of("C")),
                a -> a.add(Initializer.of()),
                a -> a.add(Method.of("a", int.class)),
                a -> a.add(Field.of("x", int.class)),
                a -> a.add(Value.ofNumber(1)),
                a -> a.imports(List.class),
                a -> a.annotate(Integer.class)
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setParent() {
        instance().setParent(Enum.of("B"));
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
    void setName() {
        instance().setName(OTHER_NAME);
        assertEquals(OTHER_NAME, instance().getName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getName() {
        assertEquals(NAME, instance().getName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getValues() {
        assertTrue(instance().getValues().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void set() {
        instance().set(Javadoc.of("C"));
        assertTrue(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getJavadoc() {
        assertFalse(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getClasses() {
        assertTrue(instance().getClasses().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getInitializers() {
        assertTrue(instance().getInitializers().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getMethods() {
        assertTrue(instance().getMethods().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getFields() {
        assertTrue(instance().getFields().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getAnnotations() {
        assertTrue(instance().getAnnotations().isEmpty());
    }
}