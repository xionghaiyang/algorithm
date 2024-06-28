package com.sean.leetcode.LeetCode16;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-21 09:36
 * @Description: https://leetcode.cn/problems/3sum-closest/
 * 16. 最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。
 * 请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 */
public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 10000000;
        for (int i = 0; i < n; i++) {
            //保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                //如果和为target直接返回答案
                if (sum == target) {
                    return target;
                }
                //根据差值的绝对值更新答案
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    //如果和大于target,向左移动k
                    int k0 = k - 1;
                    //移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        k0--;
                    }
                    k = k0;
                } else {
                    //如果和小于target,向右移动j
                    int j0 = j + 1;
                    //移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        j0++;
                    }
                    j = j0;
                }
            }
        }
        return res;
    }

}
