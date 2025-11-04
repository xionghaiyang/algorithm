package com.sean.leetcode.LeetCode2134;

/**
 * @Author xionghaiyang
 * @Date 2025-11-04 19:17
 * @Description https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii
 * 2134. 最少交换次数来组合所有的 1 II
 * 交换 定义为选中一个数组中的两个 互不相同 的位置并交换二者的值。
 * 环形 数组是一个数组，可以认为 第一个 元素和 最后一个 元素 相邻 。
 * 给你一个 二进制环形 数组 nums ，返回在 任意位置 将数组中的所有 1 聚集在一起需要的最少交换次数。
 * 1 <= nums.length <= 10^5
 * nums[i] 为 0 或者 1
 */
public class Solution {

    public int minSwaps(int[] nums) {
        int k = 0;
        for (int num : nums) {
            k += num;
        }
        if (k == 0) {
            return 0;
        }
        int n = nums.length;
        int max = 0, cnt = 0;
        for (int i = 0; i < n + k - 1; i++) {
            cnt += nums[i % n];
            if (i < k - 1) {
                continue;
            }
            max = Math.max(max, cnt);
            cnt -= nums[i - k + 1];
        }
        return k - max;
    }

}
