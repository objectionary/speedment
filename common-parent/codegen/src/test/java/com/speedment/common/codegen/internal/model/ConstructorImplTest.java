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
import com.yegor256.AggregateRepeatedTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

final class ConstructorImplTest extends AbstractTest<Constructor> {

    public ConstructorImplTest() {
        super(ConstructorImpl::new,
                a -> a.setParent(Class.of("A")),
                a -> a.javadoc(Javadoc.of("A")),
                a -> a.imports(List.class),
                a -> a.annotate(Integer.class),
                a -> a.add(Generic.of(Integer.class)),
                a -> a.add(Field.of("x", int.class)),
                a -> a.add("x = 1;"),
                a -> a.add(Integer.class),
                a -> a.field("x", int.class),
                Constructor::public_
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setParent() {
        instance().setParent(Class.of("B"));
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
    void getFields() {
        assertTrue(instance().getFields().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getCode() {
        assertTrue(instance().getCode().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getModifiers() {
        assertTrue(instance().getModifiers().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void set() {
        instance().set(Javadoc.of("A"));
        assertTrue(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getJavadoc() {
        assertFalse(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getAnnotations() {
        assertTrue(instance().getAnnotations().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getGenerics() {
        assertTrue(instance().getGenerics().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getExceptions() {
        assertTrue(instance().getExceptions().isEmpty());
    }

}