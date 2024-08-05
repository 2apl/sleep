package com.noom.interview.fullstack.sleep.controller;

import com.noom.interview.fullstack.sleep.dto.GetSleepLog;
import com.noom.interview.fullstack.sleep.dto.GetSleepLogRange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleepLoggerController {

    @GetMapping("/last-night")
    public GetSleepLog getLastNight() {
        return GetSleepLog.builder().build();
    }

    @GetMapping("/averages")
    public GetSleepLogRange getAverages() {
        return GetSleepLogRange.builder().build();
    }

    @PostMapping("/create")
    public void createSleepLog() {

    }
}
