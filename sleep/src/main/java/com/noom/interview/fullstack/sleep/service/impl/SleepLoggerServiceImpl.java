package com.noom.interview.fullstack.sleep.service.impl;

import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLogRange;
import com.noom.interview.fullstack.sleep.repository.SleepLoggerRepository;
import com.noom.interview.fullstack.sleep.service.SleepLogMapper;
import com.noom.interview.fullstack.sleep.service.SleepLoggerService;
import java.time.Clock;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SleepLoggerServiceImpl implements SleepLoggerService {

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
        return null;
    }
}
