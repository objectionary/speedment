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
package com.speedment.common.injector;

import com.yegor256.AggregateRepeatedTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

final class InjectBundleTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void empty() {
        assertEquals(0, InjectBundle.empty().injectables().count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void of() {
        final Class<?>[] classes = {String.class, Integer.class, Long.class};
        final List<Class<?>> expected = Arrays.asList(classes);
        final InjectBundle bundle = InjectBundle.of(classes);
        assertEquals(expected, bundle.injectables().collect(toList()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void withBundle() {
        final InjectBundle first = InjectBundle.of(String.class);
        final InjectBundle second = InjectBundle.of(Integer.class);
        final InjectBundle composite = first.withBundle(second);
        assertEquals(Arrays.asList(String.class, Integer.class), composite.injectables().collect(toList()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void withComponent() {
        final InjectBundle composite = InjectBundle.of(String.class).withComponent(Integer.class);
        assertEquals(Arrays.asList(String.class, Integer.class), composite.injectables().collect(toList()));
    }
}