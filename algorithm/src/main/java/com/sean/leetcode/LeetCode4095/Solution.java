package com.sean.leetcode.LeetCode4095;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-25 17:55
 * @Description: https://leetcode.cn/problems/count-frequency-staircase-subarrays-ii
 * 4095. 统计频次阶梯子数组的数目 II
 * 给你一个整数数组 nums。
 * 考虑一个子数组中的所有不同值，并按它们首次出现的顺序排列。
 * 如果其中有 k 个不同值，且第一个不同值恰好出现一次，第二个不同值恰好出现两次，以此类推，第 k 个不同值恰好出现 k 次，则称该子数组为频次阶梯。
 * 即，按首次出现的顺序排列的各个不同值，其出现次数必须恰好为 1, 2, 3, ..., k。
 * 返回 nums 中频次阶梯子数组的数量。
 * 子数组是数组中一段连续且非空的元素序列。
 * 一个值在子数组中的频次，是指它在该子数组中出现的次数。
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int countStaircase(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> pos = new HashMap<>();
        int[] idxInPos = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> ps = pos.computeIfAbsent(nums[i], e -> new ArrayList<>());
            idxInPos[i] = ps.size();
            ps.add(i);
        }
        LinkedList<Integer> firsts = new LinkedList<>();
        Map<Integer, Integer> firstPos = new HashMap<>();
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            firstPos.put(x, i);
            firsts.remove(Integer.valueOf(x));
            firsts.addFirst(x);
            int m = firsts.size();
            if (m * (m + 1) / 2 > n) {
                firsts.removeLast();
            }
            int min = n, max = 0, r = i;
            for (int cnt = 1; cnt <= firsts.size(); cnt++) {
                x = firsts.get(cnt - 1);
                r += cnt;
                List<Integer> ps = pos.get(x);
                int k = idxInPos[firstPos.get(x)] + cnt - 1;
                if (k >= ps.size()) {
                    break;
                }
                if (k < ps.size() - 1) {
                    min = Math.min(min, ps.get(k + 1));
                }
                if (r > min) {
                    break;
                }
                max = Math.max(max, ps.get(k));
                if (max < r) {
                    res++;
                }
            }
        }
        return res;
    }

}
