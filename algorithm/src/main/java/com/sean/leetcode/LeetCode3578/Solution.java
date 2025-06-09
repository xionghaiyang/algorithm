package com.sean.leetcode.LeetCode3578;

import java.util.LinkedList;

/**
 * @Author xionghaiyang
 * @Date 2025-06-09 11:45
 * @Description https://leetcode.cn/problems/count-partitions-with-max-min-difference-at-most-k/
 * 3578. 统计极差最大为 K 的分割方式数
 * 给你一个整数数组 nums 和一个整数 k。
 * 你的任务是将 nums 分割成一个或多个 非空 的连续子段，使得每个子段的 最大值 与 最小值 之间的差值 不超过 k。
 * 返回在此条件下将 nums 分割的总方法数。
 * 由于答案可能非常大，返回结果需要对 10^9 + 7 取余数。
 * 2 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        //大 -> 小
        LinkedList<Integer> max = new LinkedList<>();
        //小 -> 大
        LinkedList<Integer> min = new LinkedList<>();
        //dp[i]表示前i个元素的分割方式数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        //sum记录dp[left...right]的和
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            //入
            while (!max.isEmpty() && nums[right] > nums[max.peekLast()]) {
                max.pollLast();
            }
            max.addLast(right);
            while (!min.isEmpty() && nums[right] < nums[min.peekLast()]) {
                min.pollLast();
            }
            min.addLast(right);
            //出
            while (nums[max.peekFirst()] - nums[min.peekFirst()] > k) {
                if (max.peekFirst() == left) {
                    max.pollFirst();
                }
                if (min.peekFirst() == left) {
                    min.pollFirst();
                }
                //从sum中移除dp[left]的贡献(+MOD防止负数)
                sum = (sum - dp[left] + MOD) % MOD;
                left++;
            }
            //更新dp:sum是dp[left...right]的和，+1表示单独作为子数组的情况
            dp[right + 1] = (sum + 1) % MOD;
            //更新sum，加入新计算的dp值
            sum = (sum + dp[right + 1]) % MOD;
        }
        return dp[n];
    }

}
