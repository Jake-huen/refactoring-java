package com.example.study.proxy;

public class MainEntry {
    public static void main(String[] args) {
//        Display display = new ScreenDisplay();
//        display.print("안녕하세요");
//        display.print("면접 잘 보고 오겠습니다");
//        display.print("자기소개도 준비해야 할까요?");

        Display display = new BufferDisplay(6);
        display.print("안녕하세요");
        display.print("면접 잘 보고 오겠습니다");
        display.print("자기소개도 준비해야 할까요?");

        ((BufferDisplay) display).flush();
    }
}
