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

import java.util.Date;

public class TemporalCriteria {

    private Date startDate;
    private Date endDate;
    private String dateType;

    public TemporalCriteria( Date start, Date end, String type ) {
        if ( type == null ) {
            throw new IllegalArgumentException( "Null is not valid for dateType" );
        }
        if ( start == null && end == null ) {
            throw new IllegalArgumentException( "startDate and endDate parameters cannot both be null" );
        }
        this.startDate = start;
        this.endDate = end;
        this.dateType = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDateType() {
        return dateType;
    }

}
