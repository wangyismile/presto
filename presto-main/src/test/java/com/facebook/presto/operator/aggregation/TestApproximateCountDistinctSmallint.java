/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.operator.aggregation;

import com.facebook.presto.spi.type.Type;
import com.facebook.presto.sql.tree.QualifiedName;

import java.util.concurrent.ThreadLocalRandom;

import static com.facebook.presto.SessionTestUtils.TEST_SESSION;
import static com.facebook.presto.spi.type.DoubleType.DOUBLE;
import static com.facebook.presto.spi.type.SmallintType.SMALLINT;
import static com.facebook.presto.sql.analyzer.TypeSignatureProvider.fromTypes;

public class TestApproximateCountDistinctSmallint
        extends AbstractTestApproximateCountDistinct
{
    @Override
    public InternalAggregationFunction getAggregationFunction()
    {
        return functionManager.getAggregateFunctionImplementation(
                functionManager.resolveFunction(TEST_SESSION, QualifiedName.of("approx_distinct"), fromTypes(SMALLINT, DOUBLE)));
    }

    @Override
    public Type getValueType()
    {
        return SMALLINT;
    }

    @Override
    public Object randomValue()
    {
        return ThreadLocalRandom.current().nextLong(Short.MIN_VALUE, Short.MAX_VALUE + 1);
    }
}
