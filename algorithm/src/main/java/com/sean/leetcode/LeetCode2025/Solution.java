package com.sean.leetcode.LeetCode2025;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-06-03 19:39
 * @Description https://leetcode.cn/problems/maximum-number-of-ways-to-partition-an-array/
 * 2025. 分割数组的最多方案数
 * 给你一个下标从 0 开始且长度为 n 的整数数组 nums 。
 * 分割 数组 nums 的方案数定义为符合以下两个条件的 pivot 数目：
 * 1 <= pivot < n
 * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]
 * 同时给你一个整数 k 。
 * 你可以将 nums 中 一个 元素变为 k 或 不改变 数组。
 * 请你返回在 至多 改变一个元素的前提下，最多 有多少种方法 分割 nums 使得上述两个条件都满足。
 */
public class Solution {

    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n];
        pre[0] = nums[0];
        Map<Long, Integer> right = new HashMap<>();
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            right.put(pre[i - 1], right.getOrDefault(pre[i - 1], 0) + 1);
        }
        long total = pre[n - 1];
        int res = 0;
        if ((total & 1) == 0) {
            res = right.getOrDefault(total / 2, 0);
        }
        Map<Long, Integer> left = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int d = k - nums[i];
            //total - d的奇偶性与total + d相同
            if ((total - d) % 2 == 0) {
                res = Math.max(res, right.getOrDefault((total - d) / 2, 0) + left.getOrDefault((total + d) / 2, 0));
            }
            right.put(pre[i], right.getOrDefault(pre[i], 0) - 1);
            left.put(pre[i], left.getOrDefault(pre[i], 0) + 1);
        }
        return res;
    }

}
