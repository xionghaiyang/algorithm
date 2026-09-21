package com.sean.leetcode.LeetCode740;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 14:11
 * @Description: https://leetcode.cn/problems/delete-and-earn
 * 740. 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
 * 之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。
 * 返回你能通过这些操作获得的最大点数。
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class Solution {

    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] arr = new int[max + 1];
        for (int num : nums) {
            arr[num] += num;
        }
        return rob(arr);
    }

    private int rob(int[] nums) {
        int f0 = 0, f1 = 0;
        for (int num : nums) {
            int newF = Math.max(f1, f0 + num);
            f0 = f1;
            f1 = newF;
        }
        return f1;
    }

}
