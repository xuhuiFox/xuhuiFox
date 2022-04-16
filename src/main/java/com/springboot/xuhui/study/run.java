package com.springboot.xuhui.study;

import java.util.Timer;

public class run {


    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new timerTest(),1,60000);
    }

}
