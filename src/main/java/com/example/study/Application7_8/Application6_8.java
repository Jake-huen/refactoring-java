package com.example.study.Application7_8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application6_8 {
    public static void main(String[] args) {
        Station station = createSampleStation();
        OperationPlan operationPlan = new OperationPlan(50, 55);

        List<Reading> alerts =
                readingsOutsideRange(
                        station, operationPlan.getTemperatureFloor(), operationPlan.getTemperatureCeiling(), null);
        System.out.println("alerts = " + alerts);
    }

    public static Station createSampleStation() {
        String name = "ZB1";
        List<Reading> readings =
                List.of(
                        new Reading(47, LocalDateTime.of(2016, 11, 10, 9, 10)),
                        new Reading(53, LocalDateTime.of(2016, 11, 10, 9, 20)),
                        new Reading(58, LocalDateTime.of(2016, 11, 10, 9, 30)),
                        new Reading(53, LocalDateTime.of(2016, 11, 10, 9, 40)),
                        new Reading(51, LocalDateTime.of(2016, 11, 10, 9, 50)));

        return new Station(name, readings);
    }

    public static List<Reading> readingsOutsideRange(Station station, int min, int max, NumberRange range) {
        // 조건절에 대하여 주석을 작성 해본다면..
        // range안에 있는 필드 범위에 특정 값이 속하는지를 판단하는 함수를 만들면 된다.
        return station.getReadings().stream()
                .filter(r -> !range.contains(r.getTemp()))
                .collect(Collectors.toList());
    }


}

@Data
@AllArgsConstructor
class Station {
    private String name;
    private List<Reading> readings = new ArrayList<>();
}

@Data
@AllArgsConstructor
class Reading {
    private int temp;
    private LocalDateTime time;
}

@Data
@AllArgsConstructor
class OperationPlan {
    private int temperatureFloor;
    private int temperatureCeiling;
}

@Data
@AllArgsConstructor
class NumberRange {
    private int min;
    private int max;

    // notContains를 쓸 수도 있지만, not 연산을 해서 쓰는게 코드가 조금 더 깔금하다..
    // 컨벤션에 따라 하는게 좋지만 일반적으로는 not은 함수에 쓰이지 않는다.
    public boolean contains(int value) {
        return value >= min && value <= max;
    }
}


