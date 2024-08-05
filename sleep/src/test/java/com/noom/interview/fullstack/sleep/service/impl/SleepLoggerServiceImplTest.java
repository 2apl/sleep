package com.noom.interview.fullstack.sleep.service.impl;

import static com.noom.interview.fullstack.sleep.dto.UserFelt.GOOD;
import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.noom.interview.fullstack.sleep.domain.SleepLog;
import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.repository.SleepLoggerRepository;
import com.noom.interview.fullstack.sleep.service.SleepLogMapper;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class SleepLoggerServiceImplTest {

    private final SleepLoggerRepository repository = mock(SleepLoggerRepository.class);
    private final SleepLogMapper sleepLogMapper = new SleepLogMapper();
    private final SleepLoggerServiceImpl sleepLoggerService = new SleepLoggerServiceImpl(repository, sleepLogMapper);

    @Test
    void createLog() {
        var sleepLog = createSleepLog();

        assertDoesNotThrow(() -> sleepLoggerService.createLog(sleepLog));
    }

    @Test
    void getLastNightSleepLog() {
        var sleepLog = getSleepLog();

        when(repository.getSleepLogsByDateOfSleep(any())).thenReturn(sleepLog);

        var lastNightSleepLog = sleepLoggerService.getLastNightSleepLog();

        assertEquals(sleepLog.getDateOfSleep(), lastNightSleepLog.getDate());
        assertEquals(sleepLog.getTimeInBed(), lastNightSleepLog.getTimeInBed());
        assertEquals(sleepLog.getTimeToBed(), lastNightSleepLog.getAverageTimeToBed());
        assertEquals(sleepLog.getTimeOutOfBed(), lastNightSleepLog.getAverageTimeOutOfBed());
        assertEquals(sleepLog.getUserFelt(), lastNightSleepLog.getUserFelt());
    }

    @Test
    void getSleepLogAverages() {
    }

    private CreateSleepLog createSleepLog() {
        return CreateSleepLog.builder()
            .dateOfSleep(Instant.now())
            .timeInBed(180)
            .timeToBed(Instant.now().minus(6, HOURS))
            .timeOutOfBed(Instant.now().minus(3, HOURS))
            .userFelt(GOOD)
            .build();
    }

    private SleepLog getSleepLog() {
        return SleepLog.builder()
            .dateOfSleep(Instant.now())
            .timeInBed(180)
            .timeToBed(Instant.now().minus(6, HOURS))
            .timeOutOfBed(Instant.now().minus(3, HOURS))
            .userFelt(GOOD)
            .build();
    }
}