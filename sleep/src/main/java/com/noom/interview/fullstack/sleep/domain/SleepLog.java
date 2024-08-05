package com.noom.interview.fullstack.sleep.domain;

import com.noom.interview.fullstack.sleep.dto.UserFelt;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SleepLog {

    @Id
    private Long id;
    private Instant dateOfSleep;
    private Integer timeInBed;
    private Instant timeToBed;
    private Instant timeOutOfBed;
    private UserFelt userFelt;
}
