package com.example.study.Application7_4;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Application7_4 {
    public static void main(String[] args) {
        Order order = createSampleOrder();
        System.out.println("order.getPrice() = " + order.getPrice());
    }

    private static Order createSampleOrder() {
        return new Order(new Item(10000), 10);
    }
}

@Getter
@AllArgsConstructor
class Order {
    private Item item;
    private int quantity;

    final public double getPrice() { // final을 붙여서 읽기 전용으로 사용할 수 있는지 본다. -> 컴파일 에러가 나는지 확인해야 함.
        double discountFactor = 0.98;

        if (getBasePrice() > 1000) {
            discountFactor -= 0.03;
        }
        return getBasePrice() * discountFactor;
    }

    private int getBasePrice() {
        return quantity * item.getPrice();
    }
}

@Getter
@AllArgsConstructor
class Item {
    private int price;
}
