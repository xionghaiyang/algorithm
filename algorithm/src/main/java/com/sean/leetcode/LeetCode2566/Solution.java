package com.sean.leetcode.LeetCode2566;

/**
 * @Author xionghaiyang
 * @Date 2025-06-14 05:18
 * @Description https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit
 * 2566. 替换一个数字后的最大差值
 * 给你一个整数 num 。
 * 你知道 Danny Mittal 会偷偷将 0 到 9 中的一个数字 替换 成另一个数字。
 * 请你返回将 num 中 恰好一个 数字进行替换后，得到的最大值和最小值的差为多少。
 * 注意：
 * 当 Danny 将一个数字 d1 替换成另一个数字 d2 时，Danny 需要将 nums 中所有 d1 都替换成 d2 。
 * Danny 可以将一个数字替换成它自己，也就是说 num 可以不变。
 * Danny 可以将数字分别替换成两个不同的数字分别得到最大值和最小值。
 * 替换后得到的数字可以包含前导 0 。
 * Danny Mittal 获得周赛 326 前 10 名，让我们恭喜他。
 * 1 <= num <= 10^8
 */
public class Solution {

    public int minMaxDifference(int num) {
        return getMax(num) - getMin(num);
    }

    private int getMax(int num) {
        char[] str = String.valueOf(num).toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] != '9') {
                return replace(str, str[i] - '0', 9);
            }
        }
        return num;
    }

    private int getMin(int num) {
        String str = String.valueOf(num);
        return replace(str.toCharArray(), str.charAt(0) - '0', 0);
    }

    //将num的数字i，替换成j
    private int replace(char[] str, int i, int j) {
        int res = 0;
        for (char c : str) {
            int cur = c - '0';
            res *= 10;
            if (cur == i) {
                res += j;
            } else {
                res += cur;
            }
        }
        return res;
    }

}
