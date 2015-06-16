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
package net.di2e.ecdr.commons.filter.config;


public class AtomSearchResponseTransformerConfig {

    public enum AtomContentXmlWrapOption {
        ALWAYS_WRAP, NEVER_WRAP, WRAP_HTML_AND_TEXT
    }

    private String thumbnailLinkRelation = null;
    private String metadataLinkRelation = null;
    private String productLinkRelation = null;

    private boolean zeroBasedStartIndex = false;
    private boolean proxyProductUrl = false;

    private AtomContentXmlWrapOption wrapOption = AtomContentXmlWrapOption.NEVER_WRAP;

    public AtomSearchResponseTransformerConfig() {
        thumbnailLinkRelation = "preview";
        metadataLinkRelation = "related";
        productLinkRelation = "alternate";
    }

    public AtomContentXmlWrapOption getAtomContentXmlWrapOption() {
        return wrapOption;
    }

    public void setAtomContentXmlWrapOption( AtomContentXmlWrapOption option ) {
        wrapOption = option;
    }

    public void setMetadataLinkRelation( String rel ) {
        metadataLinkRelation = rel;
    }

    public String getMetadataLinkRelation() {
        return metadataLinkRelation;
    }

    public void setProductLinkRelation( String rel ) {
        productLinkRelation = rel;
    }

    public String getProductLinkRelation() {
        return productLinkRelation;
    }

    public String getThumbnailLinkRelation() {
        return thumbnailLinkRelation;
    }

    public void setThumbnailLinkRelation( String rel ) {
        thumbnailLinkRelation = rel;
    }

    public void setProxyProductUrl( boolean proxy ) {
        proxyProductUrl = proxy;
    }

    public boolean isProxyProductUrl() {
        return proxyProductUrl;
    }

    public void setZeroBasedStartIndex( boolean zeroBased ) {
        zeroBasedStartIndex = zeroBased;
    }

    public boolean isZeroBasedStartIndex() {
        return zeroBasedStartIndex;
    }

}
