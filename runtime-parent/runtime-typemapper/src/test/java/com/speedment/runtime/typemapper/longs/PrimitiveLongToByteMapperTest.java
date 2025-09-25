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
package com.speedment.runtime.typemapper.longs;

import com.speedment.runtime.typemapper.AbstractTypeMapperTest;
import com.yegor256.AggregateRepeatedTest;

import java.util.HashMap;
import java.util.Map;

import static com.speedment.runtime.typemapper.TypeMapper.Category;
import static com.speedment.runtime.typemapper.TypeMapper.Ordering;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PrimitiveLongToByteMapperTest extends AbstractTypeMapperTest<Long, Byte, PrimitiveLongToByteMapper> {

    PrimitiveLongToByteMapperTest() {
        super(
            Long.class,
            Byte.class,
            Category.BYTE,
            Ordering.RETAIN,
            PrimitiveLongToByteMapper::new);
    }

    @Override
    @com.yegor256.AggregateRepeatedTest(100)
    protected void getJavaType() {
        assertEquals(byte.class, typeMapper().getJavaType(column()));
    }

    @Override
    protected Map<Long, Byte> testVector() {
        Map<Long, Byte> map = new HashMap<>();
        map.put(23L, Byte.valueOf("23"));
        map.put(0L, Byte.valueOf("0"));
        map.put(127L, Byte.MAX_VALUE);
        map.put(-128L, Byte.MIN_VALUE);
        map.put(null, null);
        return map;
    }
}