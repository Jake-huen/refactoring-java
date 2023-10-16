package com.example.study.domain;

import com.example.study.dto.Invoice;
import com.example.study.dto.Play;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

@Data
@AllArgsConstructor
public class Statement {
    private Invoice invoice;
    private Play[] plays;

    public String readPlainText() throws Exception {
        int totalAmount = 0;
        String result = String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer());

        for (Invoice.Performance perf : invoice.getPerformances()) {
            // 청구 내역 출력
            result +=
                    String.format(
                            "%15s:%12s%4s석\n",
                            playFor(perf).getName(), usd(amountFor(perf)), perf.getAudience());
            totalAmount += amountFor(perf);
        }

        result += String.format("총액: %s\n", usd(totalAmount));
        result += String.format("적립 포인트: %s점\n", totalVolumeCredits());
        return result;
    }

    private int totalVolumeCredits() {
        int volumeCredits = 0;
        for (Invoice.Performance perf : invoice.getPerformances()) {
            volumeCredits += volumeCreditsFor(perf);
        }
        return volumeCredits;
    }

    private String usd(long amount) {
        NumberFormat usdFormatter = NumberFormat.getCurrencyInstance(new Locale("en-US"));
        usdFormatter.setCurrency(Currency.getInstance("USD"));
        usdFormatter.setMinimumFractionDigits(2);

        return usdFormatter.format(amount / 100);
    }

    private int volumeCreditsFor(Invoice.Performance perf) {
        // 포인트 적립
        int result = 0;
        result += Math.max(perf.getAudience() - 30, 0);

        // 희극 관객 5명마다 추가 포인트 제공
        if (playFor(perf).getType().equals("comedy")) {
            result += Math.floor(perf.getAudience() / 5);
        }
        return result;
    }

    private Play playFor(Invoice.Performance perf) {
        return Arrays.stream(plays)
                .filter(p -> p.getPlayId().equals(perf.getPlayId()))
                .findFirst()
                .get();
    }

    private int amountFor(Invoice.Performance performance) throws Exception {
        int result = 0;

        switch (playFor(performance).getType()) {
            case "tragedy":
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Exception(String.format("알 수 없는 장르: %s", playFor(performance).getType()));
        }
        return result;
    }
}
