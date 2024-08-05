package com.noom.interview.fullstack.sleep.controller;

import com.noom.interview.fullstack.sleep.dto.CreateSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLogRange;
import com.noom.interview.fullstack.sleep.service.SleepLoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SleepLoggerController {

    private final SleepLoggerService sleepLoggerService;

    @GetMapping("/last-night")
    public GetSleepLog getLastNight() {
        return sleepLoggerService.getLastNightSleepLog();
    }

    @GetMapping("/averages")
    public GetSleepLogRange getAverages() {
        return sleepLoggerService.getSleepLogAverages();
    }

    @PostMapping("/create")
    public void createSleepLog(CreateSleepLog createSleepLog) {
        sleepLoggerService.createLog(createSleepLog);
    }
}
