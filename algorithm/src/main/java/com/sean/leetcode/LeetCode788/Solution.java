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

    public int rotatedDigits1(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (process(i)) {
                res++;
            }
        }
        return res;
    }

    private boolean process(int n) {
        boolean res = false;
        while (n != 0) {
            int m = n % 10;
            if (m == 3 || m == 4 || m == 7) {
                return false;
            }
            if (!res && (m == 2 || m == 5 || m == 6 || m == 9)) {
                res = true;
            }
            n /= 10;
        }
        return res;
    }

    int[] check = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    int[][][] dp = new int[5][2][2];
    List<Integer> digits = new ArrayList<>();

    public int rotatedDigits(int n) {
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }
        Collections.reverse(digits);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return dfs(0, 1, 0);
    }

    //pos:考虑第几位数字
    //bound:从第0位到pos-1位的数是否贴着n
    //diff:从第0位到pos-1位的数是否至少出现一次 2 5 6 9
    public int dfs(int pos, int bound, int diff) {
        if (pos == digits.size()) {
            return diff;
        }
        if (dp[pos][bound][diff] != -1) {
            return dp[pos][bound][diff];
        }
        int res = 0;
        for (int i = 0; i <= (bound != 0 ? digits.get(pos) : 9); i++) {
            if (check[i] != -1) {
                res += dfs(pos + 1, bound != 0 && i == digits.get(pos) ? 1 : 0, diff != 0 || check[i] == 1 ? 1 : 0);
            }
        }
        dp[pos][bound][diff] = res;
        return res;
    }

}
