package com.fintech.orion.documentverification.template.category;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by TharinduMP on 3/21/2017.
 */
@Component
public class CommonTemplateCategory implements TemplateCategory {

    @Resource(name="templateNameToCategoryMap")
    private Map<String,String> templateNameToCategoryMapList;

    private String templateName;

    public CommonTemplateCategory(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String getCategoryName() {
        return templateNameToCategoryMapList.get(templateName);
    }
}
