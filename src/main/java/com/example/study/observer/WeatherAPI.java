package com.example.study.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherAPI implements Subject{

    float temp;
    float humidity;
    float pressure;

    List<Observer> subscribers = new ArrayList<>();

    void measurementsChanged() {
        // 현재의 온습도 데이터를 랜덤값으로 얻는 것으로 비유하였다.
        temp = new Random().nextFloat() * 100;
        humidity = new Random().nextFloat() * 100;
        pressure = new Random().nextFloat() * 100;

        notifyObservers(); // 온습도 값이 변화하면 바로 옵저버들에게 발행
    }

    @Override
    public void registerObserver(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : subscribers) {
            o.display(this);
        }
    }
}
