package com.sean.leetcode.LeetCode1278;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-03 08:08
 * @Description https://leetcode.cn/problems/palindrome-partitioning-iii/
 * 1278. 分割回文串 III
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。
 * 请你按下面的要求分割字符串：
 * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 * 请返回以这种方式分割字符串所需修改的最少字符数。
 * 1 <= k <= s.length <= 100
 * s 中只含有小写英文字母。
 */
public class Solution {

    private char[] str;
    private int[][] memoChange;
    private int[][] memoDfs;

    public int palindromePartition(String s, int k) {
        int n = s.length();
        str = s.toCharArray();
        memoChange = new int[n][n];
        for (int[] row : memoChange) {
            Arrays.fill(row, -1);
        }
        memoDfs = new int[k][n];
        for (int[] row : memoDfs) {
            Arrays.fill(row, -1);
        }
        return dfs(k - 1, n - 1);
    }

    //s[:right+1]切i刀分成i+1个子串，每个字串改成回文串的最小总修改次数
    private int dfs(int i, int right) {
        //只有一个子串
        if (i == 0) {
            return minChange(0, right);
        }
        //之前计算过
        if (memoDfs[i][right] != -1) {
            return memoDfs[i][right];
        }
        int res = Integer.MAX_VALUE;
        for (int left = i; left <= right; left++) {
            res = Math.min(res, dfs(i - 1, left - 1) + minChange(left, right));
        }
        memoDfs[i][right] = res;
        return res;
    }

    //把s[i:j+1]改成回文串的最小修改次数
    private int minChange(int i, int j) {
        //子串只有一个字母，或者子串是空串
        if (i >= j) {
            return 0;//无需修改
        }
        //之前计算过
        if (memoChange[i][j] != -1) {
            return memoChange[i][j];
        }
        int res = minChange(i + 1, j - 1);
        if (str[i] != str[j]) {
            res++;
        }
        memoChange[i][j] = res;
        return res;
    }

}
