package com.sean.leetcode;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/3/28 12:24
 * @Description: https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 * II. 0～n-1中缺失的数字
 * @version: 1.0
 */
public class LeetCodeOffer53II {

    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == mid) {
                left = left + 1;
            } else {
                right = right - 1;
            }
        }
        return left;
    }

}
