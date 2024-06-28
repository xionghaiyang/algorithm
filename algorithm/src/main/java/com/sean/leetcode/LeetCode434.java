package com.sean.leetcode;

/**
 * 字符串中的单词数
 * https://leetcode-cn.com/problems/number-of-segments-in-a-string/
 */
public class LeetCode434 {

    public static int countSegments(String s) {
        String[] words = s.split(" ");
        int res = 0;
        for (String word : words) {
            if (word.length() > 0) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countSegments("i    y"));
    }

}
