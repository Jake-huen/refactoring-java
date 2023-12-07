package com.example.study.observer;

public class KoreanUser implements Observer{
    String name;

    KoreanUser(String name) {
        this.name = name;
    }

    public void display(WeatherAPI api) {
        System.out.printf("%s님이 현재 날씨 상태를 조회함 : %.2f°C %.2fg/m3 %.2fhPa\n", name, api.temp, api.humidity, api.pressure);
    }
}
