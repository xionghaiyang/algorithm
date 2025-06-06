package com.sean.leetcode.LeetCode11;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 10:19
 * @Description: https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=leetcode-75
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。
 * 有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 */
public class Solution {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            res = Math.max(res, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

}
