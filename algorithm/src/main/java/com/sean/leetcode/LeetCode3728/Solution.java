package com.sean.leetcode.LeetCode3728;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author xionghaiyang
 * @Date 2026-03-17 12:02
 * @Description https://leetcode.cn/problems/stable-subarrays-with-equal-boundary-and-interior-sum
 * 3728. 边界与内部和相等的稳定子数组
 * 给你一个整数数组 capacity。
 * 当满足以下条件时，子数组 capacity[l..r] 被视为 稳定 数组：
 * 其长度 至少 为 3。
 * 首 元素与 尾 元素都等于它们之间所有元素的 和（即 capacity[l] = capacity[r] = capacity[l + 1] + capacity[l + 2] + ... + capacity[r - 1]）。
 * 返回一个整数，表示 稳定子数组 的数量。
 * 子数组 是数组中的连续且非空的元素序列。
 * 3 <= capacity.length <= 10^5
 * -10^9 <= capacity[i] <= 10^9
 */
public class Solution {

    public class Pair {
        private int x;
        private long s;

        public Pair(int x, long s) {
            this.x = x;
            this.s = s;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, s);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return x == pair.x && s == pair.s;
        }
    }

    public long countStableSubarrays(int[] capacity) {
        Map<Pair, Integer> map = new HashMap<>();
        long res = 0, sum = capacity[0];
        for (int right = 1; right < capacity.length; right++) {
            res += map.getOrDefault(new Pair(capacity[right], sum), 0);
            map.merge(new Pair(capacity[right - 1], capacity[right - 1] + sum), 1, Integer::sum);
            sum += capacity[right];
        }
        return res;
    }

}
