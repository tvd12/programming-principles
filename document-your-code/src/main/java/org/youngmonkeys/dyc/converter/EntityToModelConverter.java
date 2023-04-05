package org.youngmonkeys.dyc.converter;

import org.youngmonkeys.dyc.entity.Post;
import org.youngmonkeys.dyc.model.PostModel;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class EntityToModelConverter {

    public PostModel toModel(Post entity) {
        return PostModel
            .builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .type(entity.getPostType())
            .status(entity.getPostStatus())
            .build();
    }
}
