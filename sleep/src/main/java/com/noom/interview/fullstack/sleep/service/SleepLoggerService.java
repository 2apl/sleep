package com.noom.interview.fullstack.sleep.service;

import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLogRange;

public interface SleepLoggerService {

    void createLog(CreateSleepLog createSleepLog);

    GetSleepLog getLastNightSleepLog();

    GetSleepLogRange getSleepLogAverages();
}
