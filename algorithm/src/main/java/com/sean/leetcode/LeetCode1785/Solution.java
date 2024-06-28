package com.sean.leetcode.LeetCode1785;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-16 09:25
 * @Description: https://leetcode.cn/problems/minimum-elements-to-add-to-form-a-given-sum/
 * 1785. 构成特定和需要添加的最少元素
 * 给你一个整数数组 nums ，和两个整数 limit 与 goal 。
 * 数组 nums 有一条重要属性：abs(nums[i]) <= limit 。
 * 返回使数组元素总和等于 goal 所需要向数组中添加的 最少元素数量 ，添加元素 不应改变 数组中 abs(nums[i]) <= limit 这一属性。
 * 注意，如果 x >= 0 ，那么 abs(x) 等于 x ；否则，等于 -x 。
 */
public class Solution {

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        if (nums == null || nums.length == 0) {
            sum = 0;
        } else {
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
        }
        long diff = Math.abs(goal - sum);
        return (int) Math.ceil((double) diff / limit);
    }

}
