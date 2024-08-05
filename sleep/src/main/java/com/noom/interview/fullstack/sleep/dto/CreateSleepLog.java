package com.noom.interview.fullstack.sleep.dto;

import java.time.Instant;
import lombok.Builder;

@Builder
public class CreateSleepLog {

    private Instant dateOfSleep;
    private Integer timeInBed;
    private Integer totalTimeInBed;
    private UserFelt userFelt;
}
