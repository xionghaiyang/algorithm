package com.sean.leetcode.LeetCode1432;

/**
 * @Author xionghaiyang
 * @Date 2025-06-15 06:43
 * @Description https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer
 * 1432. 改变一个整数能得到的最大差值
 * 给你一个整数 num 。
 * 你可以对它进行以下步骤共计 两次：
 * 选择一个数字 x (0 <= x <= 9).
 * 选择另一个数字 y (0 <= y <= 9) 。
 * 数字 y 可以等于 x 。
 * 将 num 中所有出现 x 的数位都用 y 替换。
 * 令两次对 num 的操作得到的结果分别为 a 和 b 。
 * 请你返回 a 和 b 的 最大差值 。
 * 注意，新的整数（a 或 b）必须不能 含有前导 0，并且 非 0。
 * 1 <= num <= 10^8
 */
public class Solution {

    public int maxDiff(int num) {
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
        char[] str = String.valueOf(num).toCharArray();
        int n = str.length;
        int res = num;
        if (str[0] != '1') {
            res = replace(str, str[0] - '0', 1);
        } else {
            for (int i = 1; i < n; i++) {
                if (str[i] != str[0] && str[i] != '0') {
                    res = replace(str, str[i] - '0', 0);
                    break;
                }
            }
        }
        return res;
    }

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
