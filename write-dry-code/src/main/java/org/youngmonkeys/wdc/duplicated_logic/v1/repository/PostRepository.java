package org.youngmonkeys.wdc.duplicated_logic.v1.repository;

import org.youngmonkeys.wdc.duplicated_logic.v1.entity.Post;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;

@EzySingleton
public class PostRepository {

    public void save(Post entity) {}

    public Post findById(long id) {
        return new Post();
    }
}
