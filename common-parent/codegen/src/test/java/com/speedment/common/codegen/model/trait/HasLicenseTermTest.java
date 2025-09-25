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
package com.speedment.common.codegen.model.trait;

import com.speedment.common.codegen.model.*;
import com.speedment.common.codegen.model.Class;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

final class HasLicenseTermTest {

    private static final String TEXT = "A";

    private HasLicenseTerm<File> file;

    @BeforeEach
    void setup() {
        file = File.of("a");
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void set() {
        file.set(LicenseTerm.of(TEXT));
        assertEquals(TEXT, file.getLicenseTerm().orElseThrow(NoSuchElementException::new).getText());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void licenseTerm() {
        file.licenseTerm(TEXT);
        assertEquals(TEXT, file.getLicenseTerm().orElseThrow(NoSuchElementException::new).getText());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getLicenseTerm() {
        assertEquals(Optional.empty(), file.getLicenseTerm());
    }
}