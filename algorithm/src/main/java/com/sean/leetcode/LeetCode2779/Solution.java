package com.sean.leetcode.LeetCode2779;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-15 05:43
 * @Description https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation/
 * 2779. 数组的最大美丽值
 * 给你一个下标从 0 开始的整数数组 nums 和一个 非负 整数 k 。
 * 在一步操作中，你可以执行下述指令：
 * 在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i 。
 * 将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
 * 数组的 美丽值 定义为数组中由相等元素组成的最长子序列的长度。
 * 对数组 nums 执行上述操作任意次后，返回数组可能取得的 最大 美丽值。
 * 注意：你 只 能对每个下标执行 一次 此操作。
 * 数组的 子序列 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。
 */
public class Solution {

    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        for (int left = 0, right = 0; right < n; right++) {
            while (nums[right] - nums[left] > 2 * k) {
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int maximumBeauty1(int[] nums, int k) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] diff = new int[max + 2];
        for (int num : nums) {
            diff[Math.max(num - k, 0)]++;
            diff[Math.min(num + k + 1, max + 1)]--;
        }
        int res = 0, count = 0;
        for (int num : diff) {
            count += num;
            res = Math.max(res, count);
        }
        return res;
    }

}
