package com.sean.leetcode.LeetCodeOffer53II;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 11:21
 * @Description: https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 */
public class Solution {

    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
