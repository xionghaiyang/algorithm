package com.sean.leetcode.LeetCode2091;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-30 05:42
 * @Description: https://leetcode.cn/problems/removing-minimum-and-maximum-from-array
 * 2091. 从数组中移除最大值和最小值
 * 给你一个下标从 0 开始的数组 nums ，数组由若干 互不相同 的整数组成。
 * nums 中有一个值最小的元素和一个值最大的元素。
 * 分别称为 最小值 和 最大值 。
 * 你的目标是从数组中移除这两个元素。
 * 一次 删除 操作定义为从数组的 前面 移除一个元素或从数组的 后面 移除一个元素。
 * 返回将数组中最小值和最大值 都 移除需要的最小删除次数。
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * nums 中的整数 互不相同
 */
public class Solution {

    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int p = 0, q = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[p]) {
                p = i;
            } else if (nums[i] > nums[q]) {
                q = i;
            }
        }
        if (p > q) {
            int t = p;
            p = q;
            q = t;
        }
        return Math.min(Math.min(q + 1, n - p), p + 1 + n - q);
    }

}
