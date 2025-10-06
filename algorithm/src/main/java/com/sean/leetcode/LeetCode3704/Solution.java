package com.sean.leetcode.LeetCode3704;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-05 20:23
 * @Description https://leetcode.cn/problems/count-no-zero-pairs-that-sum-to-n
 * 3704. 统计和为 N 的无零数对
 * 一个 无零 整数是一个十进制表示中 不包含数字 0 的 正 整数。
 * 给定一个整数 n，计算满足以下条件的数对 (a, b) 的数量：
 * a 和 b 都是 无零 整数。
 * a + b = n
 * 返回一个整数，表示此类数对的数量。
 * 2 <= n <= 10^15
 */
public class Solution {

    public long countNoZeroPairs(long n) {
        char[] str = Long.toString(n).toCharArray();
        int m = str.length;
        long[][][] memo = new long[m][2][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(m - 1, false, true, str, memo);
    }

    //borrowed = true 表示被低位(i+1)借了个1
    //isNum = true表示之前填的数位，两个数都无前导零
    private long dfs(int i, boolean borrowed, boolean isNum, char[] str, long[][][] memo) {
        if (i < 0) {
            return borrowed ? 0 : 1;
        }
        int ib = borrowed ? 1 : 0, in = isNum ? 1 : 0;
        if (memo[i][ib][in] != -1) {
            return memo[i][ib][in];
        }
        int d = str[i] - '0' - (borrowed ? 1 : 0);
        long res = 0;
        //情况一：两个数位都不为0
        if (isNum) {
            res = dfs(i - 1, false, true, str, memo) * twoSumWays(d);
            res += dfs(i - 1, true, true, str, memo) * twoSumWays(d + 10);
        }
        //情况二:其中一个位数填前导0
        if (i < str.length - 1) {//不能是最低位
            if (d != 0) {
                //如果d<0，必须向高位借1
                //如果isNum = true,根据对称性，方案数要乘以2
                res += dfs(i - 1, d < 0, false, str, memo) * (isNum ? 2 : 1);
            } else if (i == 0) {//两个位数都填0，只有当i=0的时候才有解
                res++;
            }
        }
        return memo[i][ib][in] = res;
    }

    private int twoSumWays(int target) {
        return Math.max(Math.min(target - 1, 19 - target), 0);
    }

}
