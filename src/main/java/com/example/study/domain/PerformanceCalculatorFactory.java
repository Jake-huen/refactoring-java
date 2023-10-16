package com.example.study.domain;

import com.example.study.dto.Performance;
import com.example.study.dto.Play;

public class PerformanceCalculatorFactory {
    public PerformanceCalculator createPerformanceCalculator(Performance performance, Play play){
        switch (play.getType()) {
            case tragedy:
                return new TragedyCalculator(performance, play);
            case comedy:
                return new ComedyCalculator(performance, play);
            default:
                throw new IllegalStateException("해당 장르를 지원하지 않습니다");
        }
    }
}
