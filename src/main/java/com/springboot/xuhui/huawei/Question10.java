package com.springboot.xuhui.huawei;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 题目描述
 * 在一行中输入一个字符串数组，如果其中一个字符串的所有以索引0开头的子串在数组中都有，那么这个字符串就是潜在密码，
 * 在所有潜在密码中最长的是真正的密码，如果有多个长度相同的真正的密码，那么取字典序最大的为唯一的真正的密码，求唯一的真正的密码。
 * <p>
 * 输入描述
 * 无
 * <p>
 * 输出描述
 * 无
 * <p>
 * 用例
 * 输入	h he hel hell hello o ok n ni nin ninj ninja
 * 输出	ninja
 * 说明
 * 按要求，hello、ok、ninja都是潜在密码。
 * 检查长度，hello、ninja是真正的密码。
 * 检查字典序，ninja是唯一真正密码。
 * <p>
 * 输入	a b c d f
 * 输出	f
 * 说明
 * 按要求，a b c d f 都是潜在密码。
 * 检查长度，a b c d f 是真正的密码。
 * 检查字典序，f是唯一真正密码。
 * <p>
 * 题目解析
 * 我的解题思路如下：
 * 将输入的所有字符串先按照长度升序，如果长度相同，则按照字典序升序。
 * 这样最后一个字符串必然就是长度最长，字典序最大的，我们从最后一个字符串str开始：
 * 接下来，我们不停地截取str的子串，即如下闭区间的子串：
 * 0~str.length-2
 * 0~str.length-3
 * 0~str.length-4
 * ....
 * 0~2
 * 0~1
 * 0~0
 * 然后将在输入的所有字符串中查找每次截取的子串是否存在，如果多存在则返回当str作为题解，如果有一个不存在，则直接中断查找，开始下一个str
 */
public class Question10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        System.out.println(getResult(arr));
    }

    private static String getResult(String[] arr) {

        HashSet<String> strings = new HashSet<>(Arrays.asList(arr));
        Arrays.sort(arr, (a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));

        for (int i = arr.length - 1; i >= 0; i--) {
            String str = arr[i];
            boolean flg = true;
            for (int j = str.length() - 1; j > 0; j--) {
                String substring = str.substring(0, j);
                if (!strings.contains(substring)) {
                    flg = false;
                    break;
                }
            }
            if (flg) return str;
        }
        return "";
    }
}
