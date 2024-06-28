package com.sean.leetcode.LeetCode753;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-10 09:30
 * @Description: https://leetcode.cn/problems/cracking-the-safe/
 * 753. 破解保险箱
 * 有一个需要密码才能打开的保险箱。
 * 密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。
 * 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。
 * 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符.
 * 请返回一个能打开保险箱的最短字符串。
 */
public class Solution {

    Set<Integer> seen = new HashSet<>();
    StringBuilder ans = new StringBuilder();
    int highest;
    int k;

    public String crackSafe(int n, int k) {
        highest = (int) Math.pow(10, n - 1);
        this.k = k;
        dfs(0);
        for (int i = 1; i < n; i++) {
            ans.append('0');
        }
        return ans.toString();
    }

    private void dfs(int node) {
        for (int i = 0; i < k; i++) {
            int next = node * 10 + i;
            if (!seen.contains(next)) {
                seen.add(next);
                dfs(next % highest);
                ans.append(i);
            }
        }
    }

}
