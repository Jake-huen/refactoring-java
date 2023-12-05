package com.example.study.Application10_4_2;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

public class Application10_4_2 {
    public static void main(String[] args) {
        // 서인도 : B / 중국 : A
        Voyage voyage = new Voyage("서인도", 10, 0);
        List<Voyage> voyages =
                List.of(
                        new Voyage("동인도", 0, 5),
                        new Voyage("서인도", 0, 15),
                        new Voyage("중국", 0, -2),
                        new Voyage("서아프리카", 0, 7));
        History history = new History(voyages);
        Rating rating = createRating(voyage, history);
        System.out.println(rating.rating(voyage, history));
    }

    public static Rating createRating(Voyage voyage, History history) {
        if (voyage.zone.equals("중국") && History.hasChina(history)) {
            return new ExperiencedChinaRating(voyage, history);
        }
        return new Rating(voyage, history);
    }
}


class ExperiencedChinaRating extends Rating {
    public ExperiencedChinaRating(Voyage voyage, History history) {
        super(voyage, history);
    }

    @Override
    protected int captainHistoryRisk(Voyage voyage, History history) {
        return super.captainHistoryRisk(voyage, history) - 2;
    }

    @Override
    public int voyageAndHistoryLengthFactor(Voyage voyage, History history) {
        int result = 0;
        result += 3;
        if (history.getSize() > 10) result += 1;
        if (voyage.length > 12) result += 1;
        if (voyage.length > 18) result -= 1;
        return result;
    }
}

class Rating {
    Voyage voyage;
    History history;

    public Rating(Voyage voyage, History history) {
        this.voyage = voyage;
        this.history = history;
    }
    // 투자 등급
    public String rating(Voyage voyage, History history) {
        int vpf = voyageProfitFactor(voyage, history);
        int vr = voyageRisk(voyage);
        int chr = captainHistoryRisk(voyage, history);
        if (vpf * 3 > vr + chr * 2) return "A";
        return "B";
    }

    // 항해 경로 위험요소
    private int voyageRisk(Voyage voyage) {
        int result = 1;
        if (voyage.length > 4) result += 2;
        if (voyage.length > 8) result += voyage.length - 8;
        if (Stream.of("중국", "동인도").anyMatch(v -> voyage.zone.equals(v))) result += 4;
        return Math.max(result, 0);
    }

    // 선장의 항해 이력 위험 요소
    protected int captainHistoryRisk(Voyage voyage, History history) {
        int result = 1;
        if (history.getSize() < 5) result += 4;
        result += history.voyages.stream().filter(v -> v.profit < 0).count();
        // if (voyage.zone.equals("중국") && History.hasChina(history)) result -= 2; // 조건을 기반으로 인스턴스 다르게 생성
        // result의 추가적인 값 연산이 생기기 때문에 분리는 하는데 하위 클래스도 기본으로 사용해야 한다.
        return Math.max(result, 0);
    }

    // 수익 요인
    private int voyageProfitFactor(Voyage voyage, History history) {
        int result = 2;
        if (voyage.zone.equals("중국")) result += 1;
        if (voyage.zone.equals("동인도")) result += 1;
        result = voyageAndHistoryLengthFactor(voyage, history);
        return result;
    }

    protected int voyageAndHistoryLengthFactor(Voyage voyage, History history) {
        int result = 0;
        result = historyLengthFactor(history, result);
        if (voyage.length > 14) result -= 1;
        return result;
    }

    private static int historyLengthFactor(History history, int result) {
        if (history.getSize() > 8) result += 1;
        return result;
    }
}

@AllArgsConstructor
class Voyage {
    public String zone;
    public int length;
    public int profit;
}

class History {
    List<Voyage> voyages;

    public History(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public int getSize() {
        return voyages.size();
    }

    // 중국 경유 여부
    public static boolean hasChina(History history) {
        return history.voyages.stream().anyMatch(v -> v.zone.equals("중국"));
    }
}
