package com.sean.leetcode.LeetCode915;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-24 08:27
 * @Description: https://leetcode.cn/problems/partition-array-into-disjoint-intervals/
 * 915. 分割数组
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 * 用例可以保证存在这样的划分方法。
 */
public class Solution {

    public int partitionDisjoint1(int[] nums) {
        int n = nums.length;
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }
        int maxLeft = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            if (maxLeft <= minRight[i + 1]) {
                return i + 1;
            }
        }
        return n - 1;
    }

    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int leftMax = nums[0];
        int leftPos = 0;
        int curMax = nums[0];
        for (int i = 1; i < n - 1; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (nums[i] < leftMax) {
                leftMax = curMax;
                leftPos = i;
            }
        }
        return leftPos + 1;
    }

}
