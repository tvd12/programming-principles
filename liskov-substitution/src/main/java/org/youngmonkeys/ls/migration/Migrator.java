package org.youngmonkeys.ls.migration;

import com.tvd12.ezyfox.util.EzyLoggable;

public abstract class Migrator extends EzyLoggable {

    public final void migrate() {
        try {
            doMigrate();
        } catch (Exception e) {
            logger.warn("migrate error", e);
        }
    }

    protected abstract void doMigrate();
}
