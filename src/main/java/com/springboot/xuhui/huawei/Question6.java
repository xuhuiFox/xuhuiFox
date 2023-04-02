package com.springboot.xuhui.huawei;

import java.util.Arrays;
import java.util.LongSummaryStatistics;
import java.util.Scanner;

/**
 * 不含101的数
 * <p>
 * 题目描述
 * 小明在学习二进制时，发现了一类不含 101的数，也就是：
 * 将数字用二进制表示，不能出现 101 。
 * 现在给定一个整数区间 [l,r] ，请问这个区间包含了多少个不含 101 的数？
 * <p>
 * 输入描述
 * 输入的唯一一行包含两个正整数 l， r（ 1 ≤ l ≤ r ≤ 10^9）。
 * <p>
 * 输出描述
 * 输出的唯一一行包含一个整数，表示在 [l,r] 区间内一共有几个不含 101 的数。
 * <p>
 * 用例
 * 输入	1 10
 * 输出	8
 * 说明	区间 [1,10] 内， 5 的二进制表示为 101 ，10的二进制表示为 1010 ，因此区间 [ 1 , 10 ] 内有 10−2=8 个不含 101的数。
 * <p>
 * 输入	10 20
 * 输出	7
 * 说明	区间 [10,20] 内，满足条件的数字有 [12,14,15,16,17,18,19] 因此答案为 7。
 */
public class Question6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] longs = Arrays.asList(scanner.nextLine().split(" ")).stream().mapToLong(Long::parseLong).toArray();
        System.out.println(getResult(longs));
    }

    private static long getResult(long[] longs) {

        String aa;
        StringBuffer bb = new StringBuffer();
        int count = 0;
        long j;
        for (long i = longs[0]; i <= longs[1]; i++) {
            aa = "";
            j = i;
            while (j != 0) {
                aa = j % 2 + aa;
                j /= 2;
            }
            char[] charArray = aa.toCharArray();

            for (int k = 0; k < charArray.length; k++) {
                bb.append(charArray[k]);
                if (k + 1 < charArray.length) {
                    bb.append(charArray[k+1]);
                }
                if (k + 2 < charArray.length) {
                    bb.append(charArray[k+2]);
                }
                if ("101".equals(bb.toString())) {
                    count++;
                }
                bb.setLength(0);
            }
        }
        long numCount = longs[1]-longs[0]+1;
        return numCount - count;
    }
}
