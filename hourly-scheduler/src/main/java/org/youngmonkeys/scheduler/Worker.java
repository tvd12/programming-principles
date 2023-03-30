package org.youngmonkeys.scheduler;

import com.tvd12.ezyfox.util.EzyLoggable;

public abstract class Worker extends EzyLoggable {

    public final void run() {
        try {
            doRun();
        } catch (Exception e) {
            logger.warn("run worker error", e);
        }
    }

    protected abstract void doRun();
}
