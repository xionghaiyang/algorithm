package com.sean.leetcode.LeetCode4093;

import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-25 17:11
 * @Description: https://leetcode.cn/problems/maximize-sum-after-at-most-k-digit-rearrangements
 * 4093. 至多 K 次数字重排后的最大和
 * 给你一个长度为 n 的整数数组 nums 和一个整数 k。
 * 在一次操作中，你可以选择 nums 中的任意一个元素，并以任意顺序重新排列其十进制数字，组成一个新的整数。
 * 返回在执行至多 k 次操作后，数组元素之和的最大值。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= n
 */
public class Solution {

    public long maximizeSum(int[] nums, int k) {
        long res = 0;
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int num : nums) {
            res += num;
            long diff = getMax(num) - num;
            if (diff > 0) {
                heap.offer(diff);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        while (!heap.isEmpty()) {
            res += heap.poll();
        }
        return res;
    }

    private long getMax(int x) {
        if (x == 0) {
            return 0;
        }
        int[] cnt = new int[10];
        while (x > 0) {
            cnt[x % 10]++;
            x /= 10;
        }
        long res = 0;
        for (int i = 9; i >= 0; i--) {
            while (cnt[i]-- > 0) {
                res = res * 10 + i;
            }
        }
        return res;
    }

}
