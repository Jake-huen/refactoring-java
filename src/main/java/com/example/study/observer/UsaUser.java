package com.example.study.observer;

public class UsaUser implements Observer{

    String name;

    UsaUser(String name) {
        this.name = name;
    }
    @Override
    public void display(WeatherAPI api) {
        System.out.printf("%s asked for weather Information : %.2f°C %.2fg/m3 %.2fhPa\n", name, api.temp, api.humidity, api.pressure);
    }
}
