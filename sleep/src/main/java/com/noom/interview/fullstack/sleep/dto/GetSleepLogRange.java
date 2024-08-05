package com.noom.interview.fullstack.sleep.dto;

import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetSleepLogRange {

    private Instant dateFrom;
    private Instant dateTo;
    private Integer averageTimeInBed;
    private Instant averageTimeToBed;
    private Instant averageTimeOutOfBed;
    private List<UserFelt> userFeltList;
}
