package com.sean.leetcode.LeetCode1470;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-29 08:33
 * @Description: https://leetcode.cn/problems/shuffle-the-array/
 * 1470. 重新排列数组
 * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 */
public class Solution {

    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[nums.length];
        for (int i = 0; i < n; i++) {
            res[2 * i] = nums[i];
            res[2 * i + 1] = nums[n + i];
        }
        return res;
    }

}
