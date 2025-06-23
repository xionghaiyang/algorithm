package com.sean.leetcode.LeetCode2200;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-24 05:58
 * @Description https://leetcode.cn/problems/find-all-k-distant-indices-in-an-array
 * 2200. 找出数组中的所有 K 近邻下标
 * 给你一个下标从 0 开始的整数数组 nums 和两个整数 key 和 k 。
 * K 近邻下标 是 nums 中的一个下标 i ，并满足至少存在一个下标 j 使得 |i - j| <= k 且 nums[j] == key 。
 * 以列表形式返回按 递增顺序 排序的所有 K 近邻下标。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * key 是数组 nums 中的一个整数
 * 1 <= k <= nums.length
 */
public class Solution {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int[] left = new int[n];
        for (int i = 0, index = -1; i < n; i++) {
            if (nums[i] == key) {
                index = i;
            }
            left[i] = index;
        }
        int[] right = new int[n];
        for (int i = n - 1, index = n; i >= 0; i--) {
            if (nums[i] == key) {
                index = i;
            }
            right[i] = index;
        }
        for (int i = 0; i < n; i++) {
            if ((left[i] != -1 && i - left[i] <= k) || (right[i] != n && right[i] - i <= k)) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> findKDistantIndices1(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int last = -k - 1;
        for (int i = k - 1; i >= 0; i--) {
            if (nums[i] == key) {
                last = i;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i + k < n && nums[i + k] == key) {
                last = i + k;
            }
            if (last >= i - k) {
                res.add(i);
            }
        }
        return res;
    }

}
