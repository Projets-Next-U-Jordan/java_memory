package fr.thejordan.dev.helper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class TaskRunnable {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public abstract void run();

    public boolean isRunning() {
        return !executorService.isShutdown();
    }

    private void refreshExecutorService() {
        if (isRunning()) executorService.shutdown();
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void runAsync() {
        refreshExecutorService();
        executorService.execute(this::run);
    }

    public void runTaskTimer(long delay, long period) {
        refreshExecutorService();
        executorService.scheduleAtFixedRate(this::run, delay, period, TimeUnit.MILLISECONDS);
    }

    public void runTaskLater(long delay) {
        refreshExecutorService();
        executorService.schedule(this::run, delay, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        refreshExecutorService();
    }
}
