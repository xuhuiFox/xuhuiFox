package com.springboot.xuhui.huawei;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 最多颜色的车辆
 *
 * 题目描述
 * 在一个狭小的路口，每秒只能通过一辆车，假设车辆的颜色只有 3 种，找出 N 秒内经过的最多颜色的车辆数量。
 * 三种颜色编号为0 ，1 ，2
 * <p>
 * 输入描述
 * 第一行输入的是通过的车辆颜色信息
 * [0,1,1,2] 代表4 秒钟通过的车辆颜色分别是 0 , 1 , 1 , 2
 * 第二行输入的是统计时间窗，整型，单位为秒
 * <p>
 * 输出描述
 * 输出指定时间窗内经过的最多颜色的车辆数量。
 * <p>
 * 用例
 * 输入	0 1 2 1
 * 3
 * 输出	2
 * 说明	在 3 秒时间窗内，每个颜色最多出现 2 次。例如：[1,2,1]
 * <p>
 * 输入	0 1 2 1
 * 2
 * 输出	1
 * 说明	在 2 秒时间窗内，每个颜色最多出现1 次。
 * 题目解析
 * 简单的滑动窗口应用。我们可以利用相邻两个滑窗的差异比较，来避免重复的计算。
 * <p>
 * 第二个滑窗相较于第一个滑窗而言，失去了0，新增了1，
 * 因此我们不需要重新统计第二个滑窗内部各种颜色的数量，只需要在第一个滑窗的统计结果基础上，减少0颜色数量1个，增加1颜色数量1个即可。
 */
public class Question5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = Arrays.asList(scanner.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        int se = scanner.nextInt();
        System.out.println(getResult(ints, se));


    }

    private static int getResult(int[] ints, int se) {

        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 0);
        count.put(1, 0);
        count.put(2, 0);
        int j;
        int a0;
        int a1;
        int a2;
        for (int i = 0; i < ints.length; i++) {
            int se1 = se;
            j = i;
            a0 = 0;
            a1 = 0;
            a2 = 0;
            while (se1 > 0) {
                if (i + se1 > ints.length) {
                    se1 = 0;
                }
                if (ints[j] == 0) {
                    a0++;
                }
                if (ints[j] == 1) {
                    a1++;
                }
                if (ints[j] == 2) {
                    a2++;
                }
                se1--;
                j++;
            }
            if (a0 > count.get(0)) {
                count.put(0, a0);
            }
            if (a1 > count.get(1)) {
                count.put(1, a1);
            }
            if (a2 > count.get(2)) {
                count.put(2, a2);
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }


}
