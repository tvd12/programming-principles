package org.youngmonkeys.di.scheduler;

import org.youngmonkeys.scheduler.Worker;

public class CountDataWorker extends Worker {

    @Override
    protected void doRun() {
        System.out.println(
            "data count is: " + System.currentTimeMillis()
        );
    }
}
