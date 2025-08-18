package com.sean.leetcode.LeetCode679;

/**
 * @Author xionghaiyang
 * @Date 2025-08-18 08:46
 * @Description https://leetcode.cn/problems/24-game
 * 679. 24 点游戏
 * 给定一个长度为4的整数数组 cards 。
 * 你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。
 * 您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 * 你须遵守以下规则:
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 你不能把数字串在一起
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 * cards.length == 4
 * 1 <= cards[i] <= 9
 */
public class Solution {

    private static final double EPS = 1e-9;

    public boolean judgePoint24(int[] cards) {
        int a = cards[0], b = cards[1], c = cards[2], d = cards[3];
        if (process(a, b, getAllResult(c, d))) {
            return true;
        }
        if (process(a, c, getAllResult(b, d))) {
            return true;
        }
        if (process(a, d, getAllResult(b, c))) {
            return true;
        }
        if (process(b, c, getAllResult(a, d))) {
            return true;
        }
        if (process(b, d, getAllResult(a, c))) {
            return true;
        }
        if (process(c, d, getAllResult(a, b))) {
            return true;
        }
        return false;
    }

    private boolean process(double x, double y, double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (process(x, y, arr[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean process(double x, double y, double z) {
        if (process(x, getAllResult(y, z))) {
            return true;
        }
        if (process(y, getAllResult(x, z))) {
            return true;
        }
        if (process(z, getAllResult(x, y))) {
            return true;
        }
        return false;
    }

    private boolean process(double x, double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (process(x, arr[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean process(double x, double y) {
        return check(x * y) || check(x + y) || check(x / y) || check(y / x) || check(x - y) || check(y - x);
    }

    private boolean check(double x) {
        return Math.abs(x - 24) < EPS;
    }

    private double[] getAllResult(double x, double y) {
        return new double[]{x * y, x + y, x / y, y / x, x - y, y - x};
    }

}
