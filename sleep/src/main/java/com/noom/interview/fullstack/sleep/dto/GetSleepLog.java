package com.noom.interview.fullstack.sleep.dto;

import java.time.Instant;
import lombok.Builder;

@Builder
public class GetSleepLog {

    private Instant date;
    private Integer timeInBed;
    private Instant averageTimeToBed;
    private Instant averageTimeOutOfBed;
    private UserFelt userFelt;
}
