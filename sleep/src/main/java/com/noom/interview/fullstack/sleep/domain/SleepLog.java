package com.noom.interview.fullstack.sleep.domain;

import static javax.persistence.GenerationType.IDENTITY;
import com.noom.interview.fullstack.sleep.dto.UserFelt;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SleepLog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Instant dateOfSleep;
    private Integer timeInBed;
    private Instant timeToBed;
    private Instant timeOutOfBed;
    @Enumerated
    private UserFelt userFelt;
}
