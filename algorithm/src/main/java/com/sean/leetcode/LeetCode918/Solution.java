package com.sean.leetcode.LeetCode918;

import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 21:18
 * @Description: https://leetcode.cn/problems/maximum-sum-circular-subarray/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 918. 环形子数组的最大和
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * 环形数组 意味着数组的末端将会与开头相连呈环状。
 * 形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 */
public class Solution {

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[2 * n + 1];
        for (int i = 0; i < 2 * n; i++) {
            preSum[i + 1] = preSum[i] + nums[i % n];
        }
        int res = nums[0];
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.offer(0);
        for (int j = 1; j <= 2 * n; j++) {
            if (linkedList.peekFirst() < j - n) {
                linkedList.pollFirst();
            }
            res = Math.max(res, preSum[j] - preSum[linkedList.peekFirst()]);
            while (!linkedList.isEmpty() && preSum[j] <= preSum[linkedList.peekLast()]) {
                linkedList.pollLast();
            }
            linkedList.offerLast(j);
        }
        return res;
    }

}
