package com.sean.leetcode.LeetCode3587;

/**
 * @Author xionghaiyang
 * @Date 2025-06-24 22:48
 * @Description https://leetcode.cn/problems/minimum-adjacent-swaps-to-alternate-parity
 * 3587. 最小相邻交换至奇偶交替
 * 给你一个由互不相同的整数组成的数组 nums 。
 * 在一次操作中，你可以交换任意两个 相邻 元素。
 * 在一个排列中，当所有相邻元素的奇偶性交替出现，我们认为该排列是 有效排列。
 * 这意味着每对相邻元素中一个是偶数，一个是奇数。
 * 请返回将 nums 变成任意一种 有效排列 所需的最小相邻交换次数。
 * 如果无法重排 nums 来获得有效排列，则返回 -1。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * nums 中的所有元素都是 唯一 的
 */
public class Solution {

    public int minSwaps(int[] nums) {
        int n = nums.length;
        //偶/奇开头的交换次数
        int[] res = new int[2];
        //偶/奇数的数量
        int[] cnt = new int[2];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            res[num & 1] += Math.abs(i - 2 * cnt[num & 1]);
            cnt[num & 1]++;
        }
        int diff = cnt[0] - cnt[1];
        return Math.abs(diff) > 1 ? -1 :
                diff <= 0 ? (diff == 0 ? Math.min(res[0], res[1]) : res[1]) : res[0];
    }

}
