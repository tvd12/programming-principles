package org.youngmonkeys.wdc.duplicated_method.v1.service;

import org.youngmonkeys.wdc.duplicated_method.v1.converter.PageModelToEntityConverter;
import org.youngmonkeys.wdc.duplicated_method.v1.model.AddPageModel;
import org.youngmonkeys.wdc.duplicated_method.v1.repository.PageRepository;

import com.tvd12.ezyhttp.server.core.annotation.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final PageModelToEntityConverter pageModelToEntityConverter;

    public void addPage(AddPageModel model) {
        pageRepository.save(
            pageModelToEntityConverter.toEntity(model)
        );
    }
}
