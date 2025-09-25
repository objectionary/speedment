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
import com.speedment.common.codegen.model.Javadoc;
import com.speedment.common.codegen.model.JavadocTag;
import com.yegor256.AggregateRepeatedTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class JavadocImplTest extends AbstractTest<Javadoc> {

    private final static String NAME = "A";

    public JavadocImplTest() {
        super(() -> new JavadocImpl(NAME),
                a -> a.imports(List.class),
                a -> a.setParent(Class.of("A")),
                a -> a.setText("V"),
                a -> a.add(JavadocTag.of("A"))
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
    void getText() {
        assertEquals(NAME, instance().getText());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setText() {
        final String text = "C";
        instance().setText(text);
        assertEquals(text, instance().getText());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getTags() {
        assertTrue(instance().getImports().isEmpty());
    }
}