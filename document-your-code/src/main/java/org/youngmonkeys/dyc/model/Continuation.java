package org.youngmonkeys.dyc.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Continuation {
    private boolean hasNext;
    private boolean hasPrevious;
}
