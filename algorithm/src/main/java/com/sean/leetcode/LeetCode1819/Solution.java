package com.sean.leetcode.LeetCode1819;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-17 09:49
 * @Description: https://leetcode.cn/problems/number-of-different-subsequences-gcds/
 * 1819. 序列中不同最大公约数的数目
 * 给你一个由正整数组成的数组 nums 。
 * 数字序列的 最大公约数 定义为序列中所有整数的共有约数中的最大整数。
 * 例如，序列 [4,6,16] 的最大公约数是 2 。
 * 数组的一个 子序列 本质是一个序列，可以通过删除数组中的某些元素（或者不删除）得到。
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 计算并返回 nums 的所有 非空 子序列中 不同 最大公约数的 数目 。
 */
public class Solution {

    public int countDifferentSubsequenceGCDs(int[] nums) {
        int maxVal = Arrays.stream(nums).max().getAsInt();
        boolean[] occured = new boolean[maxVal + 1];
        for (int num : nums) {
            occured[num] = true;
        }
        int res = 0;
        for (int i = 1; i <= maxVal; i++) {
            int subGcd = 0;
            for (int j = i; j <= maxVal; j += i) {
                if (occured[j]) {
                    if (subGcd == 0) {
                        subGcd = j;
                    } else {
                        subGcd = gcd(subGcd, j);
                    }
                    if (subGcd == i) {
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }

}
