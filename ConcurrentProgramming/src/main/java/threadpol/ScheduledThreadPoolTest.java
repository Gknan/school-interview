package threadpol;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(10);
        scheduledExecutorService
                .schedule(new Task2(), 5, TimeUnit.SECONDS);

        scheduledExecutorService
                .scheduleAtFixedRate(new Task2(), 1, 3, TimeUnit.SECONDS);
    }
}
