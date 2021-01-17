package com.github.floushee.sbmetrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@EnableScheduling
public class Metrics {

    private final AtomicInteger testGauge;
    private final Counter testCounter;

    @Autowired
    public Metrics(MeterRegistry registry) {
        testGauge = registry.gauge("custom_cauge", new AtomicInteger(0));
        testCounter = registry.counter("custom_counter");
    }

    @Scheduled(fixedRate = 1000)
    public void scheduledTask() {
        testGauge.set(Metrics.randomNumber());
        testCounter.increment();
    }

    private static int randomNumber() {
        return new Random().nextInt(100);
    }
}
