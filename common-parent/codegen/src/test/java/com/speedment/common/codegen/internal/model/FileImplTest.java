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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

final class FileImplTest extends AbstractTest<File> {

    private final static String NAME = "A";

    public FileImplTest() {
        super(() -> new FileImpl(NAME),
                a -> a.setName("Z"),
                a -> a.javadoc(Javadoc.of("A")),
                a -> a.licenseTerm("B"),
                a -> a.imports(List.class),
                a -> a.add(Class.of("B"))
        );
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
    void set() {
        final Javadoc javadoc = Javadoc.of("Z");
        instance().set(javadoc);
        assertEquals(javadoc, instance().getJavadoc().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSet() {
        final LicenseTerm licenseTerm = LicenseTerm.of("Z");
        instance().set(licenseTerm);
        assertEquals(licenseTerm, instance().getLicenseTerm().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getJavadoc() {
        assertFalse(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getLicenseTerm() {
        assertFalse(instance().getJavadoc().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getImports() {
        assertTrue(instance().getImports().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getClasses() {
        assertTrue(instance().getClasses().isEmpty());
    }
}