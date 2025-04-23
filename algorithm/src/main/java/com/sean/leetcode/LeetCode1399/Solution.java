package com.sean.leetcode.LeetCode1399;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-04-23 08:41
 * @Description https://leetcode.cn/problems/count-largest-group
 * 1399. 统计最大组的数目
 * 给你一个整数 n 。
 * 请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
 * 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
 * 1 <= n <= 10^4
 */
public class Solution {

    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int maxCnt = 0;
        for (int i = 1; i <= n; i++) {
            int sum = getSum(i);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            if (map.get(sum) > maxCnt) {
                maxCnt = map.get(sum);
                res = 1;
            } else if (map.get(sum) == maxCnt) {
                res++;
            }
        }
        return res;
    }

    private int getSum(int i) {
        int res = 0;
        while (i > 0) {
            res += i % 10;
            i /= 10;
        }
        return res;
    }

    public int countLargestGroup1(int n) {
        //n的十进制字符串s
        char[] s = String.valueOf(n).toCharArray();
        //s的长度为m
        int m = s.length;
        //数位和至多为9m
        int[][] memo = new int[m][9 * m + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int res = 0;
        int maxCnt = 0;
        for (int target = 1; target <= 9 * m; target++) {
            int cnt = dfs(0, target, true, s, memo);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                res = 1;
            } else if (cnt == maxCnt) {
                res++;
            }
        }
        return res;
    }

    //left表示剩余数位的数位和，也就是i 到 m - 1填的数位之和必须恰好等于left
    //limitHigh 表示当前是否受到了n的约束
    //若为真，则第i位填入的数字至多为s[i],否则至多为9，这个数记作hi。
    //如果在受约束的情况下填了hi,那么后续填入的数字仍受到n的约束
    //例如n=123,那么i=0填的是1的话，i=1的这一位至多填2
    //返回构造第i位及其之后数位的合法方案数
    private int dfs(int i, int left, boolean limitHigh, char[] s, int[][] memo) {
        if (i == s.length) {
            return left == 0 ? 1 : 0;
        }
        if (!limitHigh && memo[i][left] != -1) {
            return memo[i][left];
        }
        int hi = limitHigh ? s[i] - '0' : 9;
        int res = 0;
        for (int d = 0; d <= Math.min(hi, left); d++) {
            res += dfs(i + 1, left - d, limitHigh && d == hi, s, memo);
        }
        if (!limitHigh) {
            memo[i][left] = res;
        }
        return res;
    }

}
