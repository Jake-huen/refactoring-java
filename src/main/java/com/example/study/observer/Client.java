package com.example.study.observer;

public class Client {
    public static void main(String[] args) {
        WeatherAPI api = new WeatherAPI();
        KoreanUser a = new KoreanUser("김태헌");
        api.registerObserver(a);
        api.registerObserver(new KoreanUser("홍길동"));
        api.registerObserver(new KoreanUser("임꺽정"));
        api.registerObserver(new KoreanUser("세종대왕"));
        api.registerObserver(new UsaUser("King"));

        api.measurementsChanged();

        api.removeObserver(a);
        api.measurementsChanged();
    }
}
