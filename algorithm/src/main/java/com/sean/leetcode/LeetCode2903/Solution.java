package com.sean.leetcode.LeetCode2903;

/**
 * @Author xionghaiyang
 * @Date 2024-05-25 12:30
 * @Description https://leetcode.cn/problems/find-indices-with-index-and-value-difference-i/
 * 2903. 找出满足差值条件的下标 I
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，以及整数 indexDifference 和整数 valueDifference 。
 * 你的任务是从范围 [0, n - 1] 内找出  2 个满足下述所有条件的下标 i 和 j ：
 * abs(i - j) >= indexDifference 且
 * abs(nums[i] - nums[j]) >= valueDifference
 * 返回整数数组 answer。
 * 如果存在满足题目要求的两个下标，则 answer = [i, j] ；否则，answer = [-1, -1] 。
 * 如果存在多组可供选择的下标对，只需要返回其中任意一组即可。
 * 注意：i 和 j 可能 相等 。
 */
public class Solution {

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        for (int i = 0; i < n - indexDifference; i++) {
            for (int j = i + indexDifference; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int[] findIndices1(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int minIndex = 0, maxIndex = 0;
        for (int j = indexDifference; j < n; j++) {
            int i = j - indexDifference;
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[j] - nums[minIndex] >= valueDifference) {
                return new int[]{minIndex, j};
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            if (nums[maxIndex] - nums[j] >= valueDifference) {
                return new int[]{maxIndex, j};
            }
        }
        return new int[]{-1, -1};
    }

}
