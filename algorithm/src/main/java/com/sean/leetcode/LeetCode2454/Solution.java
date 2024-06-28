package com.sean.leetcode.LeetCode2454;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-12 13:50
 * @Description: https://leetcode.cn/problems/next-greater-element-iv/
 * 2454. 下一个更大元素 IV
 * 给你一个下标从 0 开始的非负整数数组 nums 。
 * 对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。
 * 如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：
 * j > i
 * nums[j] > nums[i]
 * 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
 * 如果不存在 nums[j] ，那么第二大整数为 -1 。
 * 比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
 * 请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
 */
public class Solution {

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            res[i] = -1;
            while (!pq.isEmpty() && nums[pq.peek()] < val) {
                res[pq.poll()] = val;
            }
            while (!stack.isEmpty() && nums[stack.peek()] < val) {
                pq.offer(stack.poll());
            }
            stack.push(i);
        }
        return res;
    }

}
