package org.youngmonkeys.sc.v2.repo;

import java.util.ArrayList;
import java.util.List;

import org.youngmonkeys.sc.v1.entity.Post;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.database.annotation.EzyQuery;

@EzySingleton
public class PaginationPostRepository {
    @EzyQuery(
        "SELECT e FROM Post e " +
        "WHERE e.priority < ?0 OR (e.priority = ?0 AND e.id < ?0) " +
        "ORDER BY e.priority DESC, e.id DESC LIMIT ?2"
    )
    public List<Post> findPostsByPriorityDescAndIdDesc(
        int priorityInclusive,
        int idExclusive,
        int limit
    ) {
        return new ArrayList<>();
    }
}
