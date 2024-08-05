package com.noom.interview.fullstack.sleep.service.impl;

import static com.noom.interview.fullstack.sleep.dto.UserFelt.GOOD;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.noom.interview.fullstack.sleep.domain.SleepLog;
import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.repository.SleepLoggerRepository;
import com.noom.interview.fullstack.sleep.service.SleepLogMapper;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class SleepLoggerServiceImplTest {

    private final SleepLoggerRepository repository = mock(SleepLoggerRepository.class);
    private final SleepLogMapper sleepLogMapper = new SleepLogMapper();
    private final SleepLoggerServiceImpl sleepLoggerService = new SleepLoggerServiceImpl(repository, sleepLogMapper);

    @Test
    void createLogTest() {
        var sleepLog = createSleepLog();

        assertDoesNotThrow(() -> sleepLoggerService.createLog(sleepLog));
    }

    @Test
    void getLastNightSleepLogTest() {
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
    void getSleepLogAveragesTest() {
        var sleepLog1 = getSleepLog();
        var sleepLog2 = getSleepLog();
        var sleepLog3 = getSleepLog();
        var sleepLogList = List.of(sleepLog1, sleepLog2, sleepLog3);

        when(repository.getSleepLogsByDateOfSleepBetween(any(), any()))
            .thenReturn(sleepLogList);

        var sleepLogAverages = sleepLoggerService.getSleepLogAverages();

        assertNotNull(sleepLogAverages);
        assertEquals(Instant.now().truncatedTo(DAYS), sleepLogAverages.getDateTo());
        assertEquals(Instant.now().minus(30, DAYS).truncatedTo(DAYS), sleepLogAverages.getDateFrom());
        assertEquals(sleepLog1.getTimeInBed(), sleepLogAverages.getAverageTimeInBed());
        assertEquals(sleepLogList.size(), sleepLogAverages.getUserFeltList().size());
        assertEquals(GOOD, sleepLogAverages.getUserFeltList().stream().findAny().orElse(null));
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