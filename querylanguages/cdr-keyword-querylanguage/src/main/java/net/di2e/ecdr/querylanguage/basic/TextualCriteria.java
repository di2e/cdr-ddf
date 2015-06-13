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


public class TextualCriteria {

    private String keywords;
    private boolean isCaseSensitive;
    private boolean fuzzy;

    public TextualCriteria( String keywords, boolean isCaseSensitive, boolean fuzzy ) {
        if ( keywords == null || keywords.trim().isEmpty() ) {
            throw new IllegalArgumentException( "Null or empty String is not valid for keywords[" + keywords + "]" );
        }
        this.keywords = keywords;
        this.isCaseSensitive = isCaseSensitive;
        this.fuzzy = fuzzy;
    }

    public String getKeywords() {
        return keywords;
    }

    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    public boolean isFuzzy() {
        return fuzzy;
    }

    public void setCaseSensitive( boolean caseSensitive ) {
        this.isCaseSensitive = caseSensitive;
    }

}
