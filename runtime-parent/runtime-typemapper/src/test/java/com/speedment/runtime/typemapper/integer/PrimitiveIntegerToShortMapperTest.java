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
package com.speedment.runtime.typemapper.integer;

import com.speedment.runtime.typemapper.AbstractTypeMapperTest;
import com.yegor256.AggregateRepeatedTest;

import java.util.HashMap;
import java.util.Map;

import static com.speedment.runtime.typemapper.TypeMapper.Category;
import static com.speedment.runtime.typemapper.TypeMapper.Ordering;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PrimitiveIntegerToShortMapperTest extends AbstractTypeMapperTest<Integer, Short, PrimitiveIntegerToShortMapper> {

    PrimitiveIntegerToShortMapperTest() {
        super(Integer.class,
            Short.class,
            Category.SHORT,
            Ordering.RETAIN,
            PrimitiveIntegerToShortMapper::new);
    }

    @Override
    @com.yegor256.AggregateRepeatedTest(100)
    protected void getJavaType() {
        assertEquals(short.class, typeMapper().getJavaType(column()));
    }

    @Override
    protected Map<Integer, Short> testVector() {
        Map<Integer, Short> map = new HashMap<>();
        map.put(100, Short.valueOf("100"));
        map.put(32767, Short.MAX_VALUE);
        map.put(0, Short.valueOf("0"));
        map.put(-32768, Short.MIN_VALUE);
        map.put(null, null);
        return map;
    }
}