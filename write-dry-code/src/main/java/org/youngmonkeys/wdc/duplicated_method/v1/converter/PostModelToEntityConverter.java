package org.youngmonkeys.wdc.duplicated_method.v1.converter;

import org.youngmonkeys.wdc.duplicated_method.v1.entity.Post;
import org.youngmonkeys.wdc.duplicated_method.v1.model.AddPostModel;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class PostModelToEntityConverter {

    public Post toEntity(AddPostModel model) {
        Post post = new Post();
        post.setTitle(model.getTitle());
        post.setContent(model.getContent());
        post.setCategoryId(model.getCategoryId());
        return post;
    }
}
