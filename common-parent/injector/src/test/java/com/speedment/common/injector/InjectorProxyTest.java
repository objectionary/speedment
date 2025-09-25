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

import com.speedment.common.injector.internal.ClassUsedForTestingInternalPackage;
import com.yegor256.AggregateRepeatedTest;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

final class InjectorProxyTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void samePackageOrBelow() {
        assertPredicateCorrect(InjectorProxy.samePackageOrBelow(InjectorProxyTest.class), false);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSamePackageOrBelowTrue() {
        assertPredicateCorrect(InjectorProxy.samePackageOrBelow(InjectorProxyTest.class, true), true);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSamePackageOrBelowFalse() {
        assertPredicateCorrect(InjectorProxy.samePackageOrBelow(InjectorProxyTest.class, false), false);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSamePackageOrBelowStringTrue() {
        assertPredicateCorrect(InjectorProxy.samePackageOrBelow(InjectorProxyTest.class.getPackage().getName(), true), true);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSamePackageOrBelowStringFalse() {
        assertPredicateCorrect(InjectorProxy.samePackageOrBelow(InjectorProxyTest.class.getPackage().getName(), false), false);
    }

    void assertPredicateCorrect(final Predicate<? super Class<?>> predicate, boolean excludeInternal) {
        assertTrue(predicate.test(InjectorProxyTest.class));
        assertFalse(predicate.test(String.class));
        assertThrows(NullPointerException.class, () -> predicate.test(null));
        if (excludeInternal) {
            assertFalse(predicate.test(ClassUsedForTestingInternalPackage.class));
        } else {
            assertTrue(predicate.test(ClassUsedForTestingInternalPackage.class));
        }
    }
}