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

package com.speedment.test.connector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.speedment.runtime.core.db.ConnectionUrlGenerator;
import com.speedment.runtime.core.db.DatabaseNamingConvention;
import com.speedment.runtime.core.db.DbmsColumnHandler;
import com.speedment.runtime.core.db.DbmsType;
import com.speedment.test.connector.support.Dummies;
import com.yegor256.AggregateRepeatedTest;

import java.util.ArrayList;

/**
 * @author Mislav Milicevic
 * @since 3.2.9
 */
public interface DbmsTypeMixin {

    DbmsType getDbmsTypeInstance();

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDefaultSchemaName() {
        assertNotNull(getDbmsTypeInstance().getDefaultSchemaName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDefaultDbmsName() {
        assertNotNull(getDbmsTypeInstance().getDefaultDbmsName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDataTypes() {
        assertNotNull(getDbmsTypeInstance().getDataTypes());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void hasSchemaNames() {
        assertDoesNotThrow(() -> getDbmsTypeInstance().hasSchemaNames());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void hasDatabaseNames() {
        assertDoesNotThrow(() -> getDbmsTypeInstance().hasDatabaseNames());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void hasDatabaseUsers() {
        assertDoesNotThrow(() -> getDbmsTypeInstance().hasDatabaseUsers());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void hasServerNames() {
        assertDoesNotThrow(() -> getDbmsTypeInstance().hasServerNames());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getConnectionType() {
        assertNotNull(getDbmsTypeInstance().getConnectionType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDefaultServerName() {
        assertNotNull(getDbmsTypeInstance().getDefaultServerName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getCollateFragment() {
        assertNotNull(getDbmsTypeInstance().getCollateFragment());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getName() {
        assertNotNull(getDbmsTypeInstance().getName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDriverManagerName() {
        assertNotNull(getDbmsTypeInstance().getDriverManagerName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDefaultPort() {
        assertDoesNotThrow(() -> getDbmsTypeInstance().getDefaultPort());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDbmsNameMeaning() {
        assertNotNull(getDbmsTypeInstance().getDbmsNameMeaning());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDriverName() {
        assertNotNull(getDbmsTypeInstance().getDriverName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getMetadataHandler() {
        assertNotNull(getDbmsTypeInstance().getMetadataHandler());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getColumnHandler() {
        final DbmsColumnHandler dbmsColumnHandler = getDbmsTypeInstance().getColumnHandler();

        assertNotNull(dbmsColumnHandler);
        assertNotNull(dbmsColumnHandler.excludedInInsertStatement());
        assertNotNull(dbmsColumnHandler.excludedInUpdateStatement());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getOperationHandler() {
        assertNotNull(getDbmsTypeInstance().getOperationHandler());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getConnectionUrlGenerator() {
        final ConnectionUrlGenerator connectionUrlGenerator = getDbmsTypeInstance().getConnectionUrlGenerator();

        assertNotNull(connectionUrlGenerator);
        assertNotNull(connectionUrlGenerator.from(Dummies.dbms()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getDatabaseNamingConvention() {
        final DatabaseNamingConvention namingConvention = getDbmsTypeInstance().getDatabaseNamingConvention();
        assertNotNull(namingConvention);
        assertNotNull(namingConvention.getSchemaExcludeSet());
        assertNotNull(namingConvention.fullNameOf("schema", "table", "column"));
        assertNotNull(namingConvention.fullNameOf("schema", "table"));
        assertNotNull(namingConvention.quoteField("field"));
        assertNotNull(namingConvention.encloseField("field"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getFieldPredicateView() {
        assertNotNull(getDbmsTypeInstance().getFieldPredicateView());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void isSupported() {
        assertDoesNotThrow(() -> getDbmsTypeInstance().isSupported());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getInitialQuery() {
        assertNotNull(getDbmsTypeInstance().getInitialQuery());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getSchemaTableDelimiter() {
        assertNotNull(getDbmsTypeInstance().getSchemaTableDelimiter());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getResultSetTableSchema() {
        assertNotNull(getDbmsTypeInstance().getResultSetTableSchema());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getSkipLimitSupport() {
        assertNotNull(getDbmsTypeInstance().getSkipLimitSupport());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void applySkipLimit() {
        assertNotNull(getDbmsTypeInstance().applySkipLimit("SELECT * FROM table", new ArrayList<>(), 0, 0));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getSubSelectAlias() {
        assertNotNull(getDbmsTypeInstance().getSubSelectAlias());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    default void getSortByNullOrderInsertion() {
        assertNotNull(getDbmsTypeInstance().getSortByNullOrderInsertion());
    }
}
