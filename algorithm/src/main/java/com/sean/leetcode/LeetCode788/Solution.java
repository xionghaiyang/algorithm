package com.sean.leetcode.LeetCode788;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-25 21:07
 * @Description: https://leetcode.cn/problems/rotated-digits/
 * 788. 旋转数字
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。
 * 0, 1, 和 8 被旋转后仍然是它们自己；
 * 2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；
 * 6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 */
public class Solution {

    int[] diffs = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    char[] s;
    int[][] dp;

    public int rotatedDigits(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][2];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, 0, true);
    }

    /**
     * @param i
     * @param hasDiff 表示前面填的数字是否包含2569至少一个
     * @param isLimit 表示当前是否受到了n的约束
     * @return 构造从左往右第i位及其之后数位的合法方案数
     */
    private int dfs(int i, int hasDiff, boolean isLimit) {
        if (i == s.length) {
            return hasDiff;
        }
        if (!isLimit && dp[i][hasDiff] >= 0) {
            return dp[i][hasDiff];
        }
        int res = 0;
        int up = isLimit ? s[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            if (diffs[d] != -1) {//不是347
                res += dfs(i + 1, hasDiff | diffs[d], isLimit && d == up);
            }
        }
        if (!isLimit) {
            dp[i][hasDiff] = res;
        }
        return res;
    }

}
