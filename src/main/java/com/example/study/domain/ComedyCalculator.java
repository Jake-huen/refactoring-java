package com.example.study.domain;

import com.example.study.dto.Performance;
import com.example.study.dto.Play;

public class ComedyCalculator extends PerformanceCalculator{
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    public int getAmount() {
        int result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 * (performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    public int getVolumeCredits() {
        int result = 0;
        result += super.getVolumeCredits(); // 동일한 로직
        result += Math.floor(performance.getAudience() / 5);
        return result;
    }

}
