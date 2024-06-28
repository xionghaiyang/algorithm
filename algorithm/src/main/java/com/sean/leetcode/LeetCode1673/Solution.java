package com.sean.leetcode.LeetCode1673;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2024-05-24 11:48
 * @Description https://leetcode.cn/problems/find-the-most-competitive-subsequence/
 * 1673. 找出最具竞争力的子序列
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。
 * 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 */
public class Solution {

    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && n - i + stack.size() > k && stack.peek() > nums[i]) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        int[] res = new int[k];
        while (stack.size() > k) {
            stack.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

}
