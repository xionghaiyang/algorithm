package com.sean.leetcode.LeetCodeInterview0808;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-17 17:03
 * @Description https://leetcode.cn/problems/permutation-ii-lcci
 * 面试题 08.08. 有重复字符串的排列组合
 * 有重复字符串的排列组合。
 * 编写一种方法，计算某字符串的所有排列组合。
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class Solution {

    private char[] str;
    private int n;
    private boolean[] visited;
    StringBuilder sb = new StringBuilder();
    List<String> ans = new ArrayList<>();

    public String[] permutation(String S) {
        str = S.toCharArray();
        n = S.length();
        visited = new boolean[n];
        Arrays.sort(str);
        dfs();
        String[] res = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    private void dfs() {
        if (sb.length() == n) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && str[i] == str[i - 1] && !visited[i - 1])) {
                continue;
            }
            sb.append(str[i]);
            visited[i] = true;
            dfs();
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
