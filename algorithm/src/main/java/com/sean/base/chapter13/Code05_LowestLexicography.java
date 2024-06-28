package com.sean.base.chapter13;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-28 21:30
 * @Description: TODO
 */
public class Code05_LowestLexicography {

    public String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> res = process(strs);
        return res.size() == 0 ? "" : res.first();
    }

    //strs种左右字符串全排列，返回所有可能的结果
    private TreeSet<String> process(String[] strs) {
        TreeSet<String> res = new TreeSet<>();
        if (strs.length == 0) {
            res.add("");
            return res;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                res.add(first + cur);
            }
        }
        return res;
    }

    private String[] removeIndexString(String[] arr, int index) {
        int n = arr.length;
        String[] res = new String[n - 1];
        int resIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                res[resIndex++] = arr[i];
            }
        }
        return res;
    }

    public String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            builder.append(strs[i]);
        }
        return builder.toString();
    }

    public String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] res = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = generateRandomString(strLen);
        }
        return res;
    }

    private String generateRandomString(int strLen) {
        char[] res = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < res.length; i++) {
            int value = (int) (Math.random() * 5);
            res[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(res);
    }

    public String[] copyStringArray(String[] arr) {
        String[] res = new String[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = String.valueOf(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        Code05_LowestLexicography solution = new Code05_LowestLexicography();
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = solution.generateRandomStringArray(arrLen, strLen);
            String[] arr2 = solution.copyStringArray(arr1);
            if (!solution.lowestString1(arr1).equals(solution.lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }

}
