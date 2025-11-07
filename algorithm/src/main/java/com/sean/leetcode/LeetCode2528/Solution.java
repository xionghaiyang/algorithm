package com.sean.leetcode.LeetCode2528;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-11-07 12:16
 * @Description https://leetcode.cn/problems/maximize-the-minimum-powered-city
 * 2528. 最大化城市的最小电量
 * 给你一个下标从 0 开始长度为 n 的整数数组 stations ，其中 stations[i] 表示第 i 座城市的供电站数目。
 * 每个供电站可以在一定 范围 内给所有城市提供电力。
 * 换句话说，如果给定的范围是 r ，在城市 i 处的供电站可以给所有满足 |i - j| <= r 且 0 <= i, j <= n - 1 的城市 j 供电。
 * |x| 表示 x 的 绝对值 。
 * 比方说，|7 - 5| = 2 ，|3 - 10| = 7 。
 * 一座城市的 电量 是所有能给它供电的供电站数目。
 * 政府批准了可以额外建造 k 座供电站，你需要决定这些供电站分别应该建在哪里，这些供电站与已经存在的供电站有相同的供电范围。
 * 给你两个整数 r 和 k ，如果以最优策略建造额外的发电站，返回所有城市中，最小电量的最大值是多少。
 * 这 k 座供电站可以建在多个城市。
 * n == stations.length
 * 1 <= n <= 10^5
 * 0 <= stations[i] <= 10^5
 * 0 <= r <= n - 1
 * 0 <= k <= 10^9
 */
public class Solution {

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] cnt = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);
            cnt[left] += stations[i];
            cnt[right] -= stations[i];
        }
        long left = Arrays.stream(stations).min().getAsInt();
        long right = Arrays.stream(stations).asLongStream().sum() + k;
        long res = 0;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (check(cnt, mid, r, k)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean check(long[] cnt, long val, int r, int k) {
        int n = cnt.length - 1;
        long[] diff = cnt.clone();
        long sum = 0, remaining = k;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < val) {
                long add = val - sum;
                if (remaining < add) {
                    return false;
                }
                remaining -= add;
                int end = Math.min(n, i + 2 * r + 1);
                diff[end] -= add;
                sum += add;
            }
        }
        return true;
    }

}
