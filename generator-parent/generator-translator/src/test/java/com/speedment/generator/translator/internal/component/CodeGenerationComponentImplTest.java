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
package com.speedment.generator.translator.internal.component;

import com.speedment.common.injector.Injector;
import com.speedment.common.injector.annotation.Inject;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CodeGenerationComponentImplTest {

   private CodeGenerationComponentImpl instance;
   private Injector injectorEmpty;

   @BeforeEach
   void setup() throws InstantiationException {
        instance = new CodeGenerationComponentImpl();
        injectorEmpty = Injector.builder().build();
   }

    @com.yegor256.AggregateRepeatedTest(100)
    void setInjector() {
        instance.setInjector(injectorEmpty);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void newClass() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void newEnum() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void newInterface() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEveryTable() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEverySchema() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forEveryDbms() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void decorate() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void put() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void add() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void remove() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void translators() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void translatorKeys() {
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void findTranslator() {
    }
}