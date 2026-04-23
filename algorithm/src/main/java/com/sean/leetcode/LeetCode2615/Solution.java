package com.sean.leetcode.LeetCode2615;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-04-23 06:57
 * @Description https://leetcode.cn/problems/sum-of-distances
 * 2615. 等值距离和
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 现有一个长度等于 nums.length 的数组 arr 。
 * 对于满足 nums[j] == nums[i] 且 j != i 的所有 j ，arr[i] 等于所有 |i - j| 之和。
 * 如果不存在这样的 j ，则令 arr[i] 等于 0 。
 * 返回数组 arr 。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class Solution {

    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }
        long[] res = new long[n];
        for (List<Integer> list : map.values()) {
            int size = list.size();
            if (size == 1) {
                continue;
            }
            long[] preSum = new long[size + 1];
            for (int i = 0; i < size; i++) {
                preSum[i + 1] = preSum[i] + list.get(i);
            }
            for (int i = 0; i < size; i++) {
                int pos = list.get(i);
                long left = (long) pos * i - preSum[i];
                long right = (preSum[size] - preSum[i + 1]) - (long) pos * (size - i - 1);
                res[pos] = left + right;
            }
        }
        return res;
    }

}
