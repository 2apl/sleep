package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.domain.SleepLog;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepLoggerRepository extends JpaRepository<SleepLog, Long> {

    SleepLog getSleepLogsByDateOfSleep(Instant dateOfSleep);

    List<SleepLog> getSleepLogsByDateOfSleepBetween(Instant startDate, Instant endDate);
}
