package com.sean.leetcode.LeetCodeInterview0803;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 17:20
 * @Description https://leetcode.cn/problems/magic-index-lcci
 * 面试题 08.03. 魔术索引
 * 魔术索引。
 * 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
 * 给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
 * 若有多个魔术索引，返回索引值最小的一个。
 * nums长度在[1, 1000000]之间
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 */
public class Solution {

    public int findMagicIndex(int[] nums) {
        int n = nums.length;
        int res = -1, i = 0;
        while (i < n) {
            if (nums[i] == i) {
                res = i;
                break;
            } else if (nums[i] < i) {
                i++;
            } else {
                i = nums[i];
            }
        }
        return res;
    }

    public int findMagicIndex1(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }

    private int find(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + ((right - left) >> 1);
        int leftRes = find(nums, left, mid - 1);
        if (leftRes != -1) {
            return leftRes;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }

}
