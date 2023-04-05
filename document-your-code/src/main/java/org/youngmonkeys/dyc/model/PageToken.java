package org.youngmonkeys.dyc.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageToken {
    private String next;
    private String prev;
}
