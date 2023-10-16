package com.example.study.domain;

import com.example.study.dto.Performance;
import com.example.study.dto.Play;

public class TragedyCalculator extends PerformanceCalculator{
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    public int getAmount() {
        int result = 40000;
        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }

//    public int getVolumeCredits() {
//        return Math.max(performance.getAudience() - 30, 0);
//    }
}
