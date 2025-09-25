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
package com.speedment.common.codegenxml.internal.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.speedment.common.codegen.Generator;
import com.speedment.common.codegenxml.Document;
import com.yegor256.AggregateRepeatedTest;

final class DocumentViewTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void transform() {
        final Generator generator = Generator.forJava();

        final DocumentView documentView = new DocumentView();
        final Document document = Document.of();

        assertTrue(documentView.transform(generator, document).isPresent());
    }
}
