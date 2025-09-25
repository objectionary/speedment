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
package com.speedment.common.jvm_version;

import com.yegor256.AggregateRepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Just test that things are returned. The actual
 * values returned are tested elsewhere.
 *
 */
final class JvmVersionTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void getSpecificationTitle() {
        assertNotNull(JvmVersion.getSpecificationTitle());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSpecificationVersion() {
        assertNotNull(JvmVersion.getSpecificationVersion());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSpecificationVendor() {
        assertNotNull(JvmVersion.getSpecificationVendor());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getImplementationTitle() {
        assertNotNull(JvmVersion.getImplementationTitle());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getImplementationVersion() {
        assertNotNull(JvmVersion.getImplementationVersion());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getImplementationVendor() {
        assertNotNull(JvmVersion.getImplementationVendor());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void major() {
        assertFalse(JvmVersion.major() == 0);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void minor() {
        assertTrue(JvmVersion.minor() >= 0);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void security() {
        assertTrue(JvmVersion.security() >= 0);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isJava8() {
        // We cannot build under Java 8 anyhow...
        assertFalse(JvmVersion.isJava8());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isJava9OrHigher() {
        assertTrue(JvmVersion.isJava9OrHigher());
    }
}