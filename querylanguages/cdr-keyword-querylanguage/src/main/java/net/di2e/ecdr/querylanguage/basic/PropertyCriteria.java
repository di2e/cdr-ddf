/**
 * Copyright (C) 2014 Cohesive Integrations, LLC (info@cohesiveintegrations.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.di2e.ecdr.querylanguage.basic;


public class PropertyCriteria {

    public enum Operator {
        EQUALS, LIKE
    }

    private String property = null;
    private String value = null;
    private Operator operator = null;

    public PropertyCriteria( String property, String value, Operator op ) {
        if ( property == null || value == null || op == null ) {
            throw new IllegalArgumentException( "Property, Value and Operator must all be non null values, received property[" + property + "], value[" + value + "], operator[" + op + "]" );
        }
        this.property = property;
        this.value = value;
        this.operator = op;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

}
