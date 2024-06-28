package com.sean.leetcode.LeetCode2644;

/**
 * @Author xionghaiyang
 * @Date 2024-05-18 19:12
 * @Description https://leetcode.cn/problems/find-the-maximum-divisibility-score/
 * 2644. 找出可整除性得分最大的整数
 * 给你两个下标从 0 开始的整数数组 nums 和 divisors 。
 * divisors[i] 的 可整除性得分 等于满足 nums[j] 能被 divisors[i] 整除的下标 j 的数量。
 * 返回 可整除性得分 最大的整数 divisors[i] 。
 * 如果有多个整数具有最大得分，则返回数值最小的一个。
 */
public class Solution {

    public int maxDivScore(int[] nums, int[] divisors) {
        int n = nums.length;
        int m = divisors.length;
        int res = 0, maxScore = -1;
        for (int i = 0; i < m; i++) {
            int curScore = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] % divisors[i] == 0) {
                    curScore++;
                }
            }
            if (curScore > maxScore || (curScore == maxScore && divisors[i] < res)) {
                maxScore = curScore;
                res = divisors[i];
            }
        }
        return res;
    }

}
