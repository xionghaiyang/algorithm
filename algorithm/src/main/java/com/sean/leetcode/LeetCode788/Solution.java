package com.sean.leetcode.LeetCode788;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-25 21:07
 * @Description: https://leetcode.cn/problems/rotated-digits
 * 788. 旋转数字
 * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。
 * 要求每位数字都要被旋转。
 * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。
 * 0, 1, 和 8 被旋转后仍然是它们自己；
 * 2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；
 * 6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
 * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
 */
public class Solution {

    private static final int[] DIFFS = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};

    public int rotatedDigits(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int m = s.length;
        int[][] memo = new int[m][2];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(s, memo, 0, 0, true);
    }

    //hasDiff表示前面填的数包含2569其中一个
    //isLimit表示是否受到n的约束
    private int process(char[] s, int[][] memo, int i, int hasDiff, boolean isLimit) {
        if (i == s.length) {
            return hasDiff;
        }
        if (!isLimit && memo[i][hasDiff] != -1) {
            return memo[i][hasDiff];
        }
        int res = 0;
        for (int d = 0, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {
            if (DIFFS[d] != -1) {
                res += process(s, memo, i + 1, hasDiff | DIFFS[d], isLimit && d == up);
            }
        }
        if (!isLimit) {
            memo[i][hasDiff] = res;
        }
        return res;
    }

}
