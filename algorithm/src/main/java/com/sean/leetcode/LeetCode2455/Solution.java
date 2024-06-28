package com.sean.leetcode.LeetCode2455;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 08:10
 * @Description: https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three/
 * 2455. 可被三整除的偶数的平均值
 * 给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。
 * 注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。
 */
public class Solution {

    public int averageValue(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num % 2 == 0 && num % 3 == 0) {
                sum += num;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

}
