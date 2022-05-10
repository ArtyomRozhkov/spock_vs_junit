package com.rozhkov.spockvsjunit.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

@Service
public class TestService {

    public int doIdempotentOperation(int payload) {
        return payload * payload + 3;
    }

    public int doComplexCalculations() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        return 1;
    }

    public boolean isWorkingDay(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
