package org.youngmonkeys.sc.v1.converter;

import org.youngmonkeys.sc.v1.entity.Post;
import org.youngmonkeys.sc.v1.model.PostModel;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class EntityToModelConverter {

    public PostModel toModel(Post post) {
        return new PostModel();
    }
}
