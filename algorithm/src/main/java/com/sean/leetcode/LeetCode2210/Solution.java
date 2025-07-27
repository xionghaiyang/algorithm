package com.sean.leetcode.LeetCode2210;

/**
 * @Author xionghaiyang
 * @Date 2025-07-27 07:21
 * @Description https://leetcode.cn/problems/count-hills-and-valleys-in-an-array
 * 2210. 统计数组中峰和谷的数量
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果两侧距 i 最近的不相等邻居的值均小于 nums[i] ，则下标 i 是 nums 中，某个峰的一部分。
 * 类似地，如果两侧距 i 最近的不相等邻居的值均大于 nums[i] ，则下标 i 是 nums 中某个谷的一部分。
 * 对于相邻下标 i 和 j ，如果 nums[i] == nums[j] ， 则认为这两下标属于 同一个 峰或谷。
 * 注意，要使某个下标所做峰或谷的一部分，那么它左右两侧必须 都 存在不相等邻居。
 * 返回 nums 中峰和谷的数量。
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int countHillValley(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 1; i <= n - 2; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            int left = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > nums[i]) {
                    left = 1;
                    break;
                } else if (nums[j] < nums[i]) {
                    left = -1;
                    break;
                }
            }
            int right = 0;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    right = 1;
                    break;
                } else if (nums[j] < nums[i]) {
                    right = -1;
                    break;
                }
            }
            if (left != 0 && left == right) {
                res++;
            }
        }
        return res;
    }

}
