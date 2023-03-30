package org.youngmonkeys.wdc.duplicated_method.v1.converter;

import org.youngmonkeys.wdc.duplicated_method.v1.entity.Page;
import org.youngmonkeys.wdc.duplicated_method.v1.model.AddPageModel;
import org.youngmonkeys.wdc.duplicated_method.v1.model.AddPageModel;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class PageModelToEntityConverter {

    public Page toEntity(AddPageModel model) {
        Page page = new Page();
        page.setTitle(model.getTitle());
        page.setContent(model.getContent());
        page.setCategoryId(model.getCategoryId());
        return page;
    }
}
