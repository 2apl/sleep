package com.noom.interview.fullstack.sleep.service;

import static java.time.temporal.ChronoUnit.DAYS;
import com.noom.interview.fullstack.sleep.domain.SleepLog;
import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLog;
import org.springframework.stereotype.Component;

@Component
public class SleepLogMapper {

    public SleepLog fromCreateSleepLog(CreateSleepLog createSleepLog) {
        return SleepLog.builder()
            .dateOfSleep(createSleepLog.getDateOfSleep().truncatedTo(DAYS))
            .timeInBed(createSleepLog.getTimeInBed())
            .timeToBed(createSleepLog.getTimeToBed())
            .timeOutOfBed(createSleepLog.getTimeOutOfBed())
            .userFelt(createSleepLog.getUserFelt())
            .build();
    }

    public GetSleepLog toGetSleepLog(SleepLog sleepLog) {
        return GetSleepLog.builder()
            .date(sleepLog.getDateOfSleep())
            .timeInBed(sleepLog.getTimeInBed())
            .averageTimeToBed(sleepLog.getTimeToBed())
            .averageTimeOutOfBed(sleepLog.getTimeOutOfBed())
            .userFelt(sleepLog.getUserFelt())
            .build();
    }
}
