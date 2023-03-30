package org.youngmonkeys.wdc.duplicated_method.v2.service;

import org.youngmonkeys.wdc.duplicated_method.v1.repository.PostRepository;
import org.youngmonkeys.wdc.duplicated_method.v2.converter.ModelToEntityConverter;

import com.tvd12.ezyhttp.server.core.annotation.Service;

@Service
public class PageService extends AbstractPostService {

    public PageService(
        PostRepository postRepository,
        ModelToEntityConverter modelToEntityConverter
    ) {
        super(postRepository, modelToEntityConverter);
    }
}
