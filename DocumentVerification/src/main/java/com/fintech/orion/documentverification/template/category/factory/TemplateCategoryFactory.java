package com.fintech.orion.documentverification.template.category.factory;

import com.fintech.orion.documentverification.template.category.TemplateCategory;

/**
 * Created by TharinduMP on 3/21/2017.
 */
@FunctionalInterface
public interface TemplateCategoryFactory {
    TemplateCategory getTemplateCategory(String templateName, String type);
}
