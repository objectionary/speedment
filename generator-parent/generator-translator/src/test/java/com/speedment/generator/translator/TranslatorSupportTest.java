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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.generator.translator;

import com.speedment.common.injector.Injector;
import com.speedment.generator.translator.namer.JavaLanguageNamer;
import com.speedment.generator.translator.provider.StandardJavaLanguageNamer;
import com.speedment.runtime.config.Table;
import com.speedment.runtime.core.exception.SpeedmentException;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Per Minborg
 */
final class TranslatorSupportTest extends SimpleModel {

    private static final String PATH = "com.company.myproject.mydbms.myschema.user";
    private static final Injector INJECTOR = injector();


    private TranslatorSupport<Table> instance;

    @BeforeEach
    void setUp() throws InstantiationException {
        instance = new TranslatorSupport<>(INJECTOR, table);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testNamer() {
        assertEquals(INJECTOR.getOrThrow(JavaLanguageNamer.class), instance.namer());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDocument() {
        assertEquals(table, instance.tableOrThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEntityName() {
        assertEquals("User", instance.entityName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEntityImplName() {
        assertEquals("UserImpl", instance.entityImplName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedEntityName() {
        assertEquals("GeneratedUser", instance.generatedEntityName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedEntityImplName() {
        assertEquals("GeneratedUserImpl", instance.generatedEntityImplName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testManagerName() {
        assertEquals("UserManager", instance.managerName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testManagerImplName() {
        assertEquals("UserManagerImpl", instance.managerImplName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedManagerName() {
        assertEquals("GeneratedUserManager", instance.generatedManagerName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedManagerImplName() {
        assertEquals("GeneratedUserManagerImpl", instance.generatedManagerImplName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEntityType() {
        assertEquals(fullName("User"), instance.entityType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEntityImplType() {
        assertEquals(fullName("UserImpl"), instance.entityImplType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
     void testGeneratedEntityType() {
        assertEquals(fullNameGen("GeneratedUser"), instance.generatedEntityType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedEntityImplType() {
        assertEquals(fullNameGen("GeneratedUserImpl"), instance.generatedEntityImplType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testManagerType() {
        assertEquals(fullName("UserManager"), instance.managerType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testManagerImplType() {
        assertEquals(fullName("UserManagerImpl"), instance.managerImplType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedManagerType() {
        assertEquals(fullNameGen("GeneratedUserManager"), instance.generatedManagerType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGeneratedManagerImplType() {
        assertEquals(fullNameGen("GeneratedUserManagerImpl"), instance.generatedManagerImplType().getTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testVariableName_0args() {
        assertEquals("user", instance.variableName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testVariableName_HasAlias() {
        assertEquals("mySchema", instance.variableName(schema));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTypeName_0args() {
        assertEquals("User", instance.typeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTypeName_HasAlias() {
        assertEquals("MySchema", instance.typeName(schema));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTypeName_Project() {
        assertEquals("MyProject", instance.typeName(project));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testManagerTypeName_0args() {
        assertEquals("UserManager", instance.managerTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testManagerTypeName_HasAlias() {
        assertEquals("UserManager", instance.managerTypeName(table));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFullyQualifiedTypeName_0args() {
        assertEquals(fullName("User"), instance.fullyQualifiedTypeName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFullyQualifiedTypeName_String() {
        assertEquals(fullName("olle.User"), instance.fullyQualifiedTypeName("olle"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFullyQualifiedTypeName_String_String() {
        final String result = instance.fullyQualifiedTypeName("subPath", "filePrefix");
        assertEquals(PATH + ".subPath." + "FilePrefix" + instance.typeName(table), result);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testBasePackageName() {
        assertEquals(PATH, instance.basePackageName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testBaseDirectoryName() {
        assertEquals(PATH.replace('.', '/'), instance.baseDirectoryName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testProject() {
        assertEquals(project, instance.project().get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDbms() {
        assertEquals(dbms, instance.dbms().get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSchema() {
        assertEquals(schema, instance.schema().get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTable() {
        assertEquals(table, instance.table().get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testColumn() {
        assertEquals(Optional.empty(), instance.column());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testProjectOrThrow() {
        assertEquals(project, instance.projectOrThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDbmsOrThrow() {
        assertEquals(dbms, instance.dbmsOrThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSchemaOrThrow() {
        assertEquals(schema, instance.schemaOrThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTableOrThrow() {
        assertEquals(table, instance.tableOrThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testColumnOrThrow() {
        assertThrows(IllegalStateException.class, () -> instance.columnOrThrow());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testShortTableName() {
        final TranslatorSupport<Table> support = new TranslatorSupport<>(injector(), table2);
        assertEquals("sP", support.variableName());
    }

    private String fullName(String s) {
        return PATH + "." + s;
    }

    private String fullNameGen(String s) {
        return PATH + ".generated." + s;
    }

    private static Injector injector() {
        try {
            return Injector.builder()
                .withComponent(StandardJavaLanguageNamer.class)
                .build();
        } catch (InstantiationException e) {
            throw new SpeedmentException(e);
        }
    }

}