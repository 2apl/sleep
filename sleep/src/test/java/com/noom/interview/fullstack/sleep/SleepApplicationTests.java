package com.noom.interview.fullstack.sleep;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.dto.UserFelt;
import com.noom.interview.fullstack.sleep.repository.SleepLoggerRepository;
import com.noom.interview.fullstack.sleep.service.SleepLoggerService;
import java.time.Clock;
import java.time.Instant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SleepApplicationTests {

    @Autowired
    private SleepLoggerService sleepLoggerService;

    @Autowired
    private SleepLoggerRepository sleepLoggerRepository;

    @BeforeEach
    void clean() {
        sleepLoggerRepository.deleteAll();
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(true).isTrue();
    }

    @Test
    void createLogIntegrationTest() {
        var createSleepLog = getCreateSleepLog();

        sleepLoggerService.createLog(createSleepLog);

        var sleepLogs = sleepLoggerRepository.findAll();

        assertNotNull(sleepLogs);

        var sleepLog = sleepLogs.get(0);
        assertNotNull(sleepLog);
        assertEquals(createSleepLog.getDateOfSleep().truncatedTo(DAYS), sleepLog.getDateOfSleep());
        assertEquals(createSleepLog.getTimeToBed(), sleepLog.getTimeToBed());
        assertEquals(createSleepLog.getTimeOutOfBed(), sleepLog.getTimeOutOfBed());
        assertEquals(createSleepLog.getTimeInBed(), sleepLog.getTimeInBed());
        assertEquals(createSleepLog.getUserFelt(), sleepLog.getUserFelt());
    }

    @Test
    void getLogIntegrationTest() {
        var createSleepLog = getCreateSleepLog();
        sleepLoggerService.createLog(createSleepLog);

        var lastNightSleepLog = sleepLoggerService.getLastNightSleepLog();

        assertNotNull(lastNightSleepLog);
        assertEquals(createSleepLog.getDateOfSleep().truncatedTo(DAYS), lastNightSleepLog.getDate());
        assertEquals(createSleepLog.getTimeToBed(), lastNightSleepLog.getAverageTimeToBed());
        assertEquals(createSleepLog.getTimeOutOfBed(), lastNightSleepLog.getAverageTimeOutOfBed());
        assertEquals(createSleepLog.getTimeInBed(), lastNightSleepLog.getTimeInBed());
        assertEquals(createSleepLog.getUserFelt(), lastNightSleepLog.getUserFelt());
    }

    @Test
    void getLogIntegration1111Test() {
        var createSleepLog = getCreateSleepLog();
        sleepLoggerService.createLog(createSleepLog);
        var createSleepLog2 = getCreateSleepLog();
        sleepLoggerService.createLog(createSleepLog2);

        var lastNightSleepLog = sleepLoggerService.getSleepLogAverages();

        assertNotNull(lastNightSleepLog);
        assertEquals(createSleepLog.getDateOfSleep().truncatedTo(DAYS).minus(30, DAYS), lastNightSleepLog.getDateFrom());
        assertEquals(createSleepLog.getDateOfSleep().truncatedTo(DAYS), lastNightSleepLog.getDateTo());
        assertEquals(2, lastNightSleepLog.getUserFeltList().size());
    }

    private static CreateSleepLog getCreateSleepLog() {
        return CreateSleepLog.builder()
            .userFelt(UserFelt.GOOD)
            .dateOfSleep(Instant.now(Clock.systemUTC()))
            .timeInBed(80)
            .timeToBed(Instant.now(Clock.systemUTC()))
            .timeOutOfBed(Instant.now(Clock.systemUTC()))
            .build();
    }
}
