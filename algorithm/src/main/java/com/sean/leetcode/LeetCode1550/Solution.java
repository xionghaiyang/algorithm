package com.sean.leetcode.LeetCode1550;

/**
 * @Author xionghaiyang
 * @Date 2025-05-11 08:33
 * @Description https://leetcode.cn/problems/three-consecutive-odds
 * 1550. 存在连续三个奇数的数组
 * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 */
public class Solution {

    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if ((num & 1) != 0) {
                if (++count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

}
