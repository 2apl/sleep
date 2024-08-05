package com.noom.interview.fullstack.sleep.service.impl;

import static java.time.temporal.ChronoUnit.DAYS;
import com.noom.interview.fullstack.sleep.domain.SleepLog;
import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLogRange;
import com.noom.interview.fullstack.sleep.dto.GetSleepLogRange.GetSleepLogRangeBuilder;
import com.noom.interview.fullstack.sleep.dto.UserFelt;
import com.noom.interview.fullstack.sleep.repository.SleepLoggerRepository;
import com.noom.interview.fullstack.sleep.service.SleepLogMapper;
import com.noom.interview.fullstack.sleep.service.SleepLoggerService;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SleepLoggerServiceImpl implements SleepLoggerService {

    private static final int DAYS_FOR_AVERAGES = 30;
    private static final int ZERO = 0;
    private static final long ZERO_LONG = 0L;

    private final SleepLoggerRepository repository;
    private final SleepLogMapper sleepLogMapper;

    @Override
    public void createLog(CreateSleepLog createSleepLog) {
        var sleepLog = sleepLogMapper.fromCreateSleepLog(createSleepLog);
        repository.save(sleepLog);
    }

    @Override
    public GetSleepLog getLastNightSleepLog() {
        var sleepLog = repository.getSleepLogsByDateOfSleep(Instant.now(Clock.systemDefaultZone()));
        return sleepLogMapper.toGetSleepLog(sleepLog);
    }

    @Override
    public GetSleepLogRange getSleepLogAverages() {
        var dateTo = Instant.now(Clock.systemDefaultZone());
        var dateFrom = dateTo.minus(DAYS_FOR_AVERAGES, DAYS);

        var sleepLogs = repository.getSleepLogsByDateOfSleepBetween(dateFrom, dateTo);

        return setSleepLogRangeValues(sleepLogs, GetSleepLogRange.builder())
            .dateFrom(dateFrom.truncatedTo(DAYS))
            .dateTo(dateTo.truncatedTo(DAYS))
            .build();
    }

    private GetSleepLogRange.GetSleepLogRangeBuilder setSleepLogRangeValues(List<SleepLog> sleepLogs,
        GetSleepLogRangeBuilder builder) {
        var totalTimeInBed = ZERO;
        var totalTimeToBed = ZERO_LONG;
        var totalTimeOutOfBed = ZERO_LONG;
        var userFeltList = new ArrayList<UserFelt>();

        for (SleepLog sleepLog : sleepLogs) {
            totalTimeInBed += sleepLog.getTimeInBed();
            totalTimeToBed += sleepLog.getTimeToBed().toEpochMilli();
            totalTimeOutOfBed += sleepLog.getTimeOutOfBed().toEpochMilli();
            userFeltList.add(sleepLog.getUserFelt());
        }

        var averageTimeInBed = totalTimeInBed / sleepLogs.size();
        var averageTimeToBed = Instant.ofEpochMilli(totalTimeToBed / sleepLogs.size());
        var averageTimeOutOfBed = Instant.ofEpochMilli(totalTimeOutOfBed / sleepLogs.size());

        return builder
            .averageTimeInBed(averageTimeInBed)
            .averageTimeToBed(averageTimeToBed)
            .averageTimeOutOfBed(averageTimeOutOfBed)
            .userFeltList(userFeltList);
    }
}
