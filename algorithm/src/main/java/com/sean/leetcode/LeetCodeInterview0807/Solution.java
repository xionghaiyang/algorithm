package com.sean.leetcode.LeetCodeInterview0807;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 20:36
 * @Description https://leetcode.cn/problems/permutation-i-lcci
 * 面试题 08.07. 无重复字符串的排列组合
 * 无重复字符串的排列组合。
 * 编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class Solution {

    public String[] permutation(String S) {
        char[] str = S.toCharArray();
        int n = str.length;
        List<String> list = new ArrayList<>();
        boolean[] visited = new boolean[n];
        process(str, visited, list, new StringBuilder(), 0);
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void process(char[] str, boolean[] visited, List<String> list, StringBuilder sb, int i) {
        if (i == str.length) {
            list.add(sb.toString());
            return;
        }
        for (int j = 0; j < str.length; j++) {
            if (!visited[j]) {
                visited[j] = true;
                sb.append(str[j]);
                process(str, visited, list, sb, i + 1);
                sb.deleteCharAt(sb.length() - 1);
                visited[j] = false;
            }
        }
    }

}
