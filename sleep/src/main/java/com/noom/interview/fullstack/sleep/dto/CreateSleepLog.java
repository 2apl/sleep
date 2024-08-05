package com.noom.interview.fullstack.sleep.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateSleepLog {

    private Instant dateOfSleep;
    private Integer timeInBed;
    private Instant timeToBed;
    private Instant timeOutOfBed;
    private UserFelt userFelt;
}
