/**
 * 定时器测试
 */
package com.springboot.xuhui.study;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * @author xuhui
 */
public class timerTest extends TimerTask {


    int count = 0;
    @Override
    public void run() {
        count++;
        if (count >= 2) {
            System.out.println("开始运行：" + getTime(System.currentTimeMillis()));
            count = 0;
        }
    }

    public String getTime(long time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return sdf.format(new Date(Long.parseLong(String.valueOf(time))));
    }
}
