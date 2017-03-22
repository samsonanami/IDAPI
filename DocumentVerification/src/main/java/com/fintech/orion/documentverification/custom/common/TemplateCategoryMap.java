package com.fintech.orion.documentverification.custom.common;

import java.util.Map;

/**
 * Created by chamilk on 3/21/17.
 */
public class TemplateCategoryMap implements TemplateMap {

    private Map<String, String> categoryMap;

    public TemplateCategoryMap(Map<String, String> categoryMap) {
        this.categoryMap = categoryMap;
    }

    @Override
    public String getCategoryByTemplateName(String templateName) {
        return categoryMap.get(templateName);
    }
}
