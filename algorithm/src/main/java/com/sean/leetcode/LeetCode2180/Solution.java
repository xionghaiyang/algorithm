package com.sean.leetcode.LeetCode2180;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-06 09:21
 * @Description: https://leetcode.cn/problems/count-integers-with-even-digit-sum/
 * 2180. 统计各位数字之和为偶数的整数个数
 * 给你一个正整数 num ，请你统计并返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目。
 * 正整数的 各位数字之和 是其所有位上的对应数字相加的结果。
 */
public class Solution {

    public int countEven1(int num) {
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int x = i;
            int sum = 0;
            while (x != 0) {
                sum += x % 10;
                x /= 10;
            }
            if (sum % 2 == 0) {
                res++;
            }
        }
        return res;
    }

    public int countEven(int num) {
        int y = num / 10, x = num % 10;
        int res = y * 5, ySum = 0;
        while (y != 0) {
            ySum += y % 10;
            y /= 10;
        }
        if (ySum % 2 == 0) {
            res += x / 2 + 1;
        } else {
            res += (x + 1) / 2;
        }
        return res - 1;
    }

}
