package com.example.study.domain;

import com.example.study.dto.Invoice;
import com.example.study.dto.Performance;
import com.example.study.dto.Play;

import java.util.Arrays;

public class StatementData {

    private final Invoice invoice;
    private final Play[] plays;

    public StatementData(Invoice invoice, Play[] plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String getCustomer(){
        return invoice.getCustomer();
    }

    public Performance[] getPerformances() {
        return this.invoice.getPerformances();
    }

    public int totalAmount() throws Exception {
        int result = 0;
        for (Performance perf : invoice.getPerformances()) {
            result += amountFor(perf);
        }
        return result;
    }

    public int totalVolumeCredits() {
        int result = 0;
        for (Performance perf : invoice.getPerformances()) {
            result += volumeCreditsFor(perf);
        }
        return result;
    }

    public Play playFor(Performance perf) {
        return Arrays.stream(plays)
                .filter(p -> p.getPlayId().equals(perf.getPlayId()))
                .findFirst()
                .get();
    }

    public int amountFor(Performance performance) throws Exception {
        // 타입에 따라 유연하게 확장을 위해 Factory Class를 사용함
        PerformanceCalculatorFactory performanceCalculatorFactory = new PerformanceCalculatorFactory();
        return performanceCalculatorFactory
                .createPerformanceCalculator(performance, playFor(performance))
                .getAmount();
    }

    private int volumeCreditsFor(Performance performance) {
        PerformanceCalculatorFactory performanceCalculatorFactory = new PerformanceCalculatorFactory();
        return performanceCalculatorFactory
                .createPerformanceCalculator(performance, playFor(performance))
                .getVolumeCredits();
    }

    // PerformanceCalculatorFactory: 타입에 맞는 계산기 구현체를 반환
    // PerformanceCalculator: 추상 클래스로, 각 계산기 구현체의 상위 클래스로 사용이 됨(추상화 목적)
    // 각각의 XXXCalculator: 타입에 맞는 계산기 클래스, 각 타입에 해당하는
}
