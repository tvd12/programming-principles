package org.youngmonkeys.di.scheduler;

import org.youngmonkeys.scheduler.Worker;

public class DoSomethingWorker extends Worker {
    @Override
    protected void doRun() {
        System.out.println("do something");
    }
}
