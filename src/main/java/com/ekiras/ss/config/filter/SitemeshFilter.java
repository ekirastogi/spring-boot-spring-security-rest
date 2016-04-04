package com.ekiras.ss.config.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author ekansh
 * @since 4/4/16
 */
public class SitemeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/admin/**", "/WEB-INF/sitemesh/admin.jsp");
        builder.addDecoratorPath("/auth/**", "/WEB-INF/sitemesh/auth.jsp");
        builder.addDecoratorPath("/user/**", "/WEB-INF/sitemesh/user.jsp");
        builder.addDecoratorPath("/**", "/WEB-INF/sitemesh/default.jsp");
    }

}
