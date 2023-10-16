package com.example.study.domain;

import com.example.study.dto.Invoice;
import com.example.study.dto.Performance;
import com.example.study.dto.Play;

public abstract class PerformanceCalculator {

    protected Performance performance;
    protected Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public abstract int getAmount() throws Exception;

    public int getVolumeCredits(){
        return Math.max(performance.getAudience() - 30, 0);
    }
}
