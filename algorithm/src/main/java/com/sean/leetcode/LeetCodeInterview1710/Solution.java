package com.sean.leetcode.LeetCodeInterview1710;

/**
 * @Author xionghaiyang
 * @Date 2026-03-26 10:37
 * @Description https://leetcode.cn/problems/find-majority-element-lcci
 * 面试题 17.10. 主要元素
 * 数组中占比超过一半的元素称之为主要元素。
 * 给你一个 整数 数组，找出其中的主要元素。
 * 若没有，返回 -1 。
 * 请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 */
public class Solution {

    public int majorityElement(int[] nums) {
        int res = -1, cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                res = num;
            }
            if (num == res) {
                cnt++;
            } else {
                cnt--;
            }
        }
        cnt = 0;
        for (int num : nums) {
            if (num == res) {
                cnt++;
            }
        }
        return 2 * cnt > nums.length ? res : -1;
    }

}
