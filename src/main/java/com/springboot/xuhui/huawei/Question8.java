package com.springboot.xuhui.huawei;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Question8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(getResult(m, nums));
    }

    private static int getResult(int m, int[] nums) {
        int i = 0;
        Arrays.sort(nums);
        int min = nums[nums.length - 1];
        while (true) {
            if (i > 0) {
                min += nums[i - 1];
            }
            boolean flg = getOtherResult(min, nums, i);
            if (flg) {
                break;
            } else {
                i++;
            }
        }
        return min;
    }

    private static boolean getOtherResult(int min, int[] nums, int i) {

        int[] clones = nums.clone();
        List<Integer> cloneList = Arrays.stream(clones).boxed().collect(Collectors.toList());

        cloneList.remove(cloneList.size() - 1);
        int min1;
        min1 = cloneList.get(cloneList.size() - 1);
        for (int j = 0; j < i; j++) {
            cloneList.remove(j);
        }
        boolean flg = true;
        if (cloneList.size() == 0) return flg;
        for (int j = 0; j < i; j++) {
            min1 += cloneList.get(j);
        }
        while (cloneList.size() != 0) {
            if (min1 == min) {
                int[] ints = listToArray(cloneList);
                return getOtherResult(min1, ints, i);
            }
            if (min1 < min) {
                i++;
                min1 += cloneList.get(i - 1);
            }
            if (min1 > min) {
                flg = false;
                break;
            }
        }
        return flg;
    }

    private static int[] listToArray(List<Integer> cloneList) {
        int[] result = new int[cloneList.size()];
        for (int i = 0; i < cloneList.size(); i++) {
            result[i] = cloneList.get(i);
        }
        return result;
    }
}
