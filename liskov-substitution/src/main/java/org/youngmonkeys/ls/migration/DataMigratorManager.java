package org.youngmonkeys.ls.migration;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.tvd12.ezyfox.bean.EzySingletonFactory;

@SuppressWarnings("unchecked")
public class DataMigratorManager {

    private final AtomicBoolean started;
    private final List<Migrator> migrators;

    public DataMigratorManager(
        EzySingletonFactory singletonFactory
    ) {
        this.started = new AtomicBoolean();
        this.migrators = singletonFactory
            .getSingletonsOf(Migrator.class);
    }

    public void startMigration() {
        if (started.compareAndSet(false, true)) {
            ScheduledExecutorService scheduler = Executors
                .newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(
                this::migrate,
                100,
                100,
                TimeUnit.MILLISECONDS
            );
        }
    }

    private void migrate() {
        migrators.forEach(Migrator::migrate);
    }
}
