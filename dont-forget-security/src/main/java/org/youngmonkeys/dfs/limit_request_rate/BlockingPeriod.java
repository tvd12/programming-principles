package org.youngmonkeys.dfs.limit_request_rate;

import lombok.Getter;

@Getter
public class BlockingPeriod {
    private final long startTime;
    private final long endedTime;

    public BlockingPeriod(long duration) {
        this.startTime = System.currentTimeMillis();
        this.endedTime = this.startTime + duration;
    }

    public boolean isEnded(long timestamp) {
        return timestamp > endedTime;
    }
}
