package com.fintech.orion.documentverification.template.category.factory;

import com.fintech.orion.documentverification.template.category.CommonTemplateCategory;
import com.fintech.orion.documentverification.template.category.TemplateCategory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TharinduMP on 3/22/2017.
 */
public class CommonTemplateCategoryFactory implements TemplateCategoryFactory {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public TemplateCategory getTemplateCategory(String templateName, String type) {
        if(type.equals("Common")) {
            return beanFactory.getBean(CommonTemplateCategory.class, templateName);
        }
        return null;
    }

}
