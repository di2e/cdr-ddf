/**
 * Copyright (c) Cohesive Integrations, LLC
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or any later version. 
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. A copy of the GNU Lesser General Public License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 * 
 **/
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
