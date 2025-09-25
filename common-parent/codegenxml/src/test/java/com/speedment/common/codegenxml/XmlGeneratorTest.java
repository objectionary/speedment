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
package com.speedment.common.codegenxml;

import static com.speedment.common.codegenxml.Elements.body;
import static com.speedment.common.codegenxml.Elements.head;
import static com.speedment.common.codegenxml.Elements.html;
import static com.speedment.common.codegenxml.Elements.title;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.speedment.common.codegen.TransformFactory;
import com.speedment.common.codegen.provider.StandardTransformFactory;
import com.speedment.common.codegen.util.Formatting;
import com.speedment.common.codegenxml.internal.view.DocumentView;
import com.yegor256.AggregateRepeatedTest;

import java.util.ArrayList;

/**
 *
 * @author Per Minborg
 */
final class XmlGeneratorTest {

    private final XmlGenerator generator = new XmlGenerator();

    @com.yegor256.AggregateRepeatedTest(100)
    void testSimpleHTML() {
        final Document doc = Document.html(
            html()
                .add(head()
                    .add(title().add("Welcome"))
                ).add(body()
                    .add("The content of the document")
                )
        );

        Formatting.tab("    ");
        final String html = new XmlGenerator().on(doc).get();

        assertTrue(html.contains("Welcome"));
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testSimpleXML() {
        final Document doc = Document.xml(
            TagElement.of("project")
                .add(TagElement.of("dependencies")
                    .add(TagElement.of("dependency").add("Speedment"))
                )
        );

        Formatting.tab("    ");
        final String html = new XmlGenerator().on(doc).get();

        assertTrue(html.contains("Speedment"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDependencyMgr() {
        assertNotNull(generator.getDependencyMgr());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getRenderStack() {
        assertNotNull(generator.getRenderStack());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void metaOn() {
        assertNotNull(generator.metaOn(Document.of(), String.class));
        assertNotNull(generator.metaOn(Document.of(), String.class, DocumentView.class));
        assertNotNull(generator.metaOn(Document.of()));
        assertNotNull(generator.metaOn(new ArrayList<>()));
        assertNotNull(generator.metaOn(new ArrayList<>(), String.class));
        assertNotNull(generator.metaOn(new ArrayList<>(), String.class, DocumentView.class));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void on() {
        assertNotNull(generator.on(""));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void onEach() {
        assertNotNull(generator.onEach(new ArrayList<>()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void transform() {
        final TransformFactory transformFactory = new StandardTransformFactory("factory");

        assertNotNull(generator.transform(new DocumentView(), Document.of(), transformFactory));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void forJava() {
        assertNotNull(XmlGenerator.forJava());
    }
}
