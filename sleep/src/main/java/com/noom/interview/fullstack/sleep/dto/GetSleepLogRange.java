package com.noom.interview.fullstack.sleep.dto;

import java.time.Instant;
import java.util.List;
import lombok.Builder;

@Builder
public class GetSleepLogRange {

    private String dateFrom;
    private String dateTo;
    private Integer averageTimeInBed;
    private Instant averageTimeToBed;
    private Instant averageTimeOutOfBed;
    private List<UserFelt> userFeltList;
}
