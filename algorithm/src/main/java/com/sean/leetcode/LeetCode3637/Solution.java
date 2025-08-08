package com.sean.leetcode.LeetCode3637;

/**
 * @Author xionghaiyang
 * @Date 2025-08-08 17:45
 * @Description https://leetcode.cn/problems/trionic-array-i
 * 3637. 三段式数组 I
 * 给你一个长度为 n 的整数数组 nums。
 * 如果存在索引 0 < p < q < n − 1，使得数组满足以下条件，则称其为 三段式数组（trionic）：
 * nums[0...p] 严格 递增，
 * nums[p...q] 严格 递减，
 * nums[q...n − 1] 严格 递增。
 * 如果 nums 是三段式数组，返回 true；否则，返回 false。
 * 3 <= n <= 100
 * -1000 <= nums[i] <= 1000
 */
public class Solution {

    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int[][] transfer = {{1, -1}, {1, 2}, {3, 2}, {3, -1}};
        int state = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                state = transfer[state][0];
            } else if (nums[i] < nums[i - 1]) {
                state = transfer[state][1];
            } else {
                return false;
            }
            if (state == -1) {
                return false;
            }
        }
        return state == 3;
    }

}
