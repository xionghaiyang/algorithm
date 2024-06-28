package com.sean.leetcode.LeetCode2386;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-09 19:11
 * @Description: https://leetcode.cn/problems/find-the-k-sum-of-an-array/
 * 2386. 找出数组的第 K 大和
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * 你可以选择数组的任一 子序列 并且对其全部元素求和。
 * 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复）
 * 返回数组的 第 k 大和 。
 * 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。
 * 注意：空子序列的和视作 0 。
 */
public class Solution {

    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>((a, b) -> Long.compare(a.getKey(), b.getKey()));
        pq.offer(new Pair<>(0L, 0));
        while (--k > 0) {
            Pair<Long, Integer> pair = pq.poll();
            long s = pair.getKey();
            int i = pair.getValue();
            if (i < n) {
                //在子序列的末尾添加num[i]
                pq.offer(new Pair<>(s + nums[i], i + 1));//一个添加/替换的元素下标为i+1
                if (i > 0) {
                    //替换子序列的末尾元素为num[i]
                    pq.offer(new Pair<>(s + nums[i] - nums[i - 1], i + 1));
                }
            }
        }
        return sum - pq.peek().getKey();
    }

}
