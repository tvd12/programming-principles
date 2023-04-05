package org.youngmonkeys.dyc.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginationModel<T> {

    private List<T> items;
    private PageToken pageToken;
    private Continuation continuation;
    private int count;
    private long total;
}
