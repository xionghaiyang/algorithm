package com.sean.leetcode;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 */
public class LeetCode131 {

    private boolean[][] dp;
    private List<List<String>> res = new ArrayList<List<String>>();
    private List<String> lists = new ArrayList<String>();
    private int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = true;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            res.add(new ArrayList<>(lists));
            return;
        }
        for (int j = i; j < n; j++) {
            if (dp[i][j]) {
                lists.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                lists.remove(lists.size() - 1);
            }
        }
    }

    private void printList(List<List<String>> lists) {
        if (lists == null || lists.size() == 0) {
            return;
        }
        for (int i = 0; i < lists.size(); i++) {
            List<String> list = lists.get(i);
            list.forEach(s -> System.out.print(s + " "));
            System.out.println();
        }
    }

}
