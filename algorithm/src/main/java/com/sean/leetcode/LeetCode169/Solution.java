package com.sean.leetcode.LeetCode169;

/**
 * @Author xionghaiyang
 * @Date 2025-06-14 19:06
 * @Description https://leetcode.cn/problems/majority-element
 * 169. 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。
 * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public int majorityElement(int[] nums) {
        int n = nums.length;
        int count = 1, res = nums[0];
        for (int i = 1; i < n; i++) {
            if (count == 0) {
                res = nums[i];
            }
            if (res == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return res;
    }

}
