package com.sean.leetcode.LeetCode3331;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Author xionghaiyang
 * @Date 2025-11-04 13:23
 * @Description https://leetcode.cn/problems/find-x-sum-of-all-k-long-subarrays-ii
 * 3321. 计算子数组的 x-sum II
 * 给你一个由 n 个整数组成的数组 nums，以及两个整数 k 和 x。
 * 数组的 x-sum 计算按照以下步骤进行：
 * 统计数组中所有元素的出现次数。
 * 仅保留出现次数最多的前 x 个元素的每次出现。
 * 如果两个元素的出现次数相同，则数值 较大 的元素被认为出现次数更多。
 * 计算结果数组的和。
 * 注意，如果数组中的不同元素少于 x 个，则其 x-sum 是数组的元素总和。
 * 返回一个长度为 n - k + 1 的整数数组 answer，其中 answer[i] 是 子数组 nums[i..i + k - 1] 的 x-sum。
 * 子数组 是数组内的一个连续 非空 的元素序列。
 * nums.length == n
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= x <= k <= nums.length
 */
public class Solution {

    private final TreeSet<int[]> L = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
    private final TreeSet<int[]> R = new TreeSet<>(L.comparator());
    private final Map<Integer, Integer> cnt = new HashMap<>();
    private long sumL = 0;

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] res = new long[n - k + 1];
        for (int right = 0; right < n; right++) {
            int in = nums[right];
            del(in);
            cnt.merge(in, 1, Integer::sum);
            add(in);

            int left = right + 1 - k;
            if (left < 0) {
                continue;
            }

            while (!R.isEmpty() && L.size() < x) {
                r2l();
            }
            while (L.size() > x) {
                l2r();
            }
            res[left] = sumL;

            int out = nums[left];
            del(out);
            cnt.merge(out, -1, Integer::sum);
            add(out);
        }
        return res;
    }

    private void del(int val) {
        int c = cnt.getOrDefault(val, 0);
        if (c == 0) {
            return;
        }
        int[] p = {c, val};
        if (L.contains(p)) {
            sumL -= (long) p[0] * p[1];
            L.remove(p);
        } else {
            R.remove(p);
        }
    }

    private void add(int val) {
        int c = cnt.get(val);
        if (c == 0) {
            return;
        }
        int[] p = {c, val};
        //p比L中最小的还大
        if (!L.isEmpty() && L.comparator().compare(p, L.first()) > 0) {
            sumL += (long) p[0] * p[1];
            L.add(p);
        } else {
            R.add(p);
        }
    }

    private void l2r() {
        int[] p = L.pollFirst();
        sumL -= (long) p[0] * p[1];
        R.add(p);
    }

    private void r2l() {
        int[] p = R.pollLast();
        sumL += (long) p[0] * p[1];
        L.add(p);
    }

}
