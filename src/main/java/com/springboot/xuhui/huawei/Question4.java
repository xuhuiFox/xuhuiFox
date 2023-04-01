package com.springboot.xuhui.huawei;

import java.awt.print.Printable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 在第一人称射击游戏中，玩家通过键盘的A、S、D、W四个按键控制游戏人物分别向左、向后、向右、向前进行移动，从而完成走位。
 * 假设玩家每按动一次键盘，游戏任务会向某个方向移动一步，如果玩家在操作一定次数的键盘并且各个方向的步数相同时，此时游戏任务必定会回到原点，则称此次走位为完美走位。
 * 现给定玩家的走位（例如：ASDA），请通过更换其中一段连续走位的方式使得原走位能够变成一个完美走位。其中待更换的连续走位可以是相同长度的任何走位。
 * 请返回待更换的连续走位的最小可能长度。
 * 如果原走位本身是一个完美走位，则返回0。
 * <p>
 * 输入描述
 * 输入为由键盘字母表示的走位s，例如：ASDA
 * <p>
 * 输出描述
 * 输出为待更换的连续走位的最小可能长度。
 * <p>
 * 用例
 * 输入	WASDAASD
 * 输出	1
 * 说明	将第二个A替换为W，即可得到完美走位
 * 输入	AAAA
 * 输出	3
 * 说明	将其中三个连续的A替换为WSD，即可得到完美走位
 * <p>
 * 题目解析
 * 题目要求，保持W,A,S,D字母个数平衡，即相等，如果不相等，可以从字符串中选取一段连续子串替换，来让字符串平衡。
 * 比如：WWWWAAAASSSS
 * 字符串长度12，W,A,S,D平衡的话，则每个字母个数应该是3个，而现在W,A,S各有4个，也就是说各超了1个。
 * 因此我们应该从字符串中，选取一段包含1个W，1个A，1个S的子串，来替换为D。
 * WWWWAAAASSSS
 * WWWWAAAASSSS
 * WWWWAAAASSSS
 * ........
 * <p>
 * WWWWAAAASSSS
 * 而符合这种要求的子串可能很多，我们需要找出其中最短的，即WAAAAS。
 * 本题其实就是求最小覆盖子串，同LeetCode - 76
 */
public class Question4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String zouwei = scanner.nextLine();
        System.out.println(getResult(zouwei));
    }

    private static int getResult(String zouwei) {

        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < zouwei.length(); i++) {
            count.put(zouwei.charAt(i), count.getOrDefault(zouwei.charAt(i), 0) + 1);
        }
        boolean flg = true;
        int total = 0;
        int avg = zouwei.length() / 4;
        for (Character character : count.keySet()) {
            if (count.get(character) > avg) {
                flg = false;
                count.put(character, count.get(character) - avg);
                total += count.get(character);
            } else {
                count.put(character, 0);
            }
        }
        if (flg) return 0;

        int i = 0;
        int j = 0;
        int minLen = zouwei.length() + 1;
        while (j < zouwei.length()) {
            char c = zouwei.charAt(j);
            if (count.get(c) > 0) {
                total--;
            }
            count.put(c, count.get(c) - 1);
            while (total == 0) {
                minLen = Math.min(minLen, j - i + 1);
                char ic = zouwei.charAt(i);
                if (count.get(ic) >= 0) {
                    total++;
                }
                count.put(ic, count.get(ic) + 1);
                i++;
            }
            j++;
        }
        return minLen;
    }

}
