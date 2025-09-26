package com.sean.leetcode.LeetCode611;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-09-26 09:32
 * @Description https://leetcode.cn/problems/valid-triangle-number
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class Solution {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, k = i; j < n; j++) {
                while (k + 1 < n && nums[k + 1] < nums[i] + nums[j]) {
                    k++;
                }
                res += Math.max(k - j, 0);
            }
        }
        return res;
    }

}
