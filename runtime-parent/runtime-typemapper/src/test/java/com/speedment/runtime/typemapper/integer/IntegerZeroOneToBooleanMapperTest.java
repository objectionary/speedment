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
import com.speedment.runtime.typemapper.TypeMapper;
import com.yegor256.AggregateRepeatedTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

final class IntegerZeroOneToBooleanMapperTest extends AbstractTypeMapperTest<Integer, Boolean, IntegerZeroOneToBooleanMapper> {

    IntegerZeroOneToBooleanMapperTest() {
        super(
            Integer.class,
            Boolean.class,
            TypeMapper.Category.BOOLEAN,
            IntegerZeroOneToBooleanMapper::new
        );
    }

    @Override
    protected Map<Integer, Boolean> testVector() {
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, true);
        map.put(0, false);
        map.put(null, null);
        return map;
    }

    @Override
    @com.yegor256.AggregateRepeatedTest(100)
    protected void getJavaType() {
        when(column().isNullable()).thenReturn(true);
        assertEquals(Boolean.class, typeMapper().getJavaType(column()));
    }
}