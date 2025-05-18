package com.sean.leetcode.LeetCode3024;

/**
 * @Author xionghaiyang
 * @Date 2025-05-19 06:28
 * @Description https://leetcode.cn/problems/type-of-triangle
 * 3024. 三角形类型
 * 给你一个下标从 0 开始长度为 3 的整数数组 nums ，需要用它们来构造三角形。
 * 如果一个三角形的所有边长度相等，那么这个三角形称为 equilateral 。
 * 如果一个三角形恰好有两条边长度相等，那么这个三角形称为 isosceles 。
 * 如果一个三角形三条边的长度互不相同，那么这个三角形称为 scalene 。
 * 如果这个数组无法构成一个三角形，请你返回字符串 "none" ，否则返回一个字符串表示这个三角形的类型。
 * nums.length == 3
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public String triangleType(int[] nums) {
        if (nums[0] + nums[1] <= nums[2] || nums[0] + nums[2] <= nums[1] || nums[1] + nums[2] <= nums[0]) {
            return "none";
        }
        int cnt = (nums[0] == nums[1] ? 1 : 0) + (nums[0] == nums[2] ? 1 : 0) + (nums[1] == nums[2] ? 1 : 0);
        if (cnt == 0) {
            return "scalene";
        } else if (cnt == 1) {
            return "isosceles";
        } else {
            return "equilateral";
        }
    }

}
