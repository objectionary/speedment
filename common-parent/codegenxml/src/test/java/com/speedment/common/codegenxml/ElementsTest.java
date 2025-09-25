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

import static org.junit.jupiter.api.Assertions.*;

import com.yegor256.AggregateRepeatedTest;

final class ElementsTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void rows() {
        assertNotNull(Elements.rows("string"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void a() {
        assertNotNull(Elements.a());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void body() {
        assertNotNull(Elements.body());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void br() {
        assertNotNull(Elements.br());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void div() {
        assertNotNull(Elements.div());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void form() {
        assertNotNull(Elements.form());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void input() {
        assertNotNull(Elements.input());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void html() {
        assertNotNull(Elements.html());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void head() {
        assertNotNull(Elements.head());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void h1() {
        assertNotNull(Elements.h1());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void h2() {
        assertNotNull(Elements.h2());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void h3() {
        assertNotNull(Elements.h3());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void h4() {
        assertNotNull(Elements.h4());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void h5() {
        assertNotNull(Elements.h5());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void img() {
        assertNotNull(Elements.img());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void meta() {
        assertNotNull(Elements.meta());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void p() {
        assertNotNull(Elements.p());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void span() {
        assertNotNull(Elements.span());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void table() {
        assertNotNull(Elements.table());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tbody() {
        assertNotNull(Elements.tbody());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void td() {
        assertNotNull(Elements.td());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tfoot() {
        assertNotNull(Elements.tfoot());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void title() {
        assertNotNull(Elements.title());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void th() {
        assertNotNull(Elements.th());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void tr() {
        assertNotNull(Elements.tr());
    }
}
