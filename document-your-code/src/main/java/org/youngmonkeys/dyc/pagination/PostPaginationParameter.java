package org.youngmonkeys.dyc.pagination;

public interface PostPaginationParameter extends PaginationParameter {
    String paginationCondition(boolean nextPage);

    String orderBy(boolean nextPage);

    PostPaginationSortOrder sortOrder();
}
