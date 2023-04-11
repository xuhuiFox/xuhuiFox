package com.springboot.xuhui.huawei;

import java.util.*;

/**
 * 题目描述
 * 数字0、1、2、3、4、5、6、7、8、9分别关联 a~z 26个英文字母。
 * <p>
 * 0 关联 “a”,”b”,”c”
 * 1 关联 “d”,”e”,”f”
 * 2 关联 “g”,”h”,”i”
 * 3 关联 “j”,”k”,”l”
 * 4 关联 “m”,”n”,”o”
 * 5 关联 “p”,”q”,”r”
 * 6 关联 “s”,”t”
 * 7 关联 “u”,”v”
 * 8 关联 “w”,”x”
 * 9 关联 “y”,”z”
 * 例如7关联”u”,”v”，8关联”x”,”w”，输入一个字符串例如“78”，和一个屏蔽字符串“ux”，
 * 那么“78”可以组成多个字符串例如：“ux”，“uw”，“vx”，“vw”，过滤这些完全包含屏蔽字符串的每一个字符的字符串，然后输出剩下的字符串。
 * <p>
 * 用例
 * 输入	78
 * ux
 * 输出	uw vx vw
 * 说明	ux完全包含屏蔽字符串ux，因此剔除
 * 题目解析
 * 本题可以使用深度优先搜索DFS。
 * <p>
 * 本题类似于LeetCode - 17 电话号码的字母组合_伏城之外的博客-CSDN博客
 */
public class Question9 {
    static String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqr", "st", "uv", "wx", "yz"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] arr =
                Arrays.stream(sc.next().split("")).map(Integer::parseInt).toArray(Integer[]::new);
        String filter = sc.next();

        System.out.println(getResult(arr, filter));
    }

    public static String getResult(Integer[] arr, String filter) {
        String[] newArr = Arrays.stream(arr).map(val -> map[val]).toArray(String[]::new);

        char[] cArr = filter.toCharArray();
        Arrays.sort(cArr);
        filter = new String(cArr);

        ArrayList<String> res = new ArrayList<>();
        dfs(newArr, 0, new LinkedList<>(), res, filter);

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (String str : res) {
            sj.add(str);
        }
        return sj.toString();
    }

    public static void dfs(
            String[] arr, int index, LinkedList<Character> path, ArrayList<String> res, String filter) {
        if (index == arr.length) {
            // 过滤这些完全包含屏蔽字符串的每一个字符的字符串
            if (!include(path, filter)) {
                StringBuilder sb = new StringBuilder();
                for (Character c : path) {
                    sb.append(c);
                }
                res.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < arr[index].length(); i++) {
            path.addLast(arr[index].charAt(i));
            dfs(arr, index + 1, path, res, filter);
            path.removeLast();
        }
    }

    public static boolean include(LinkedList<Character> path, String filter) {
        StringBuilder sb = new StringBuilder();
        path.stream().sorted().forEach(sb::append);
        String src = sb.toString();

        if (filter.length() > src.length()) return false;

        int i = 0;
        int j = 0;

        while (i < src.length() && j < filter.length()) {
            if (src.charAt(i) == filter.charAt(j)) j++;
            i++;
        }

        return j == filter.length();

    }
}
