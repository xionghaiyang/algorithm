package com.sean.leetcode.LeetCode3116;

import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-21 05:40
 * @Description: https://leetcode.cn/problems/kth-smallest-amount-with-single-denomination-combination
 * 3116. 单面值组合的第 K 小金额
 * 给你一个整数数组 coins 表示不同面额的硬币，另给你一个整数 k 。
 * 你有无限量的每种面额的硬币。
 * 但是，你 不能 组合使用不同面额的硬币。
 * 返回使用这些硬币能制造的 第 k 小 金额。
 * 1 <= coins.length <= 15
 * 1 <= coins[i] <= 25
 * 1 <= k <= 2 * 10^9
 * coins 包含两两不同的整数。
 */
public class Solution {

    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        for (int i = 0; i < n; i++) {
            heap.offer(new long[]{(long) coins[i], 1, i});
        }
        for (int i = 0; i < k - 1; i++) {
            long cur = heap.peek()[0];
            while (heap.peek()[0] == cur) {
                long[] arr = heap.poll();
                arr[0] += coins[(int) arr[2]];
                arr[1]++;
                heap.offer(arr);
            }
        }
        return heap.peek()[0];
    }

    public long findKthSmallest1(int[] coins, int k) {
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            min = Math.min(min, coin);
        }
        long left = k, right = (long) min * k, res = right;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            if (check(mid, coins, k)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean check(long m, int[] coins, int k) {
        long cnt = 0;
        int n = coins.length;
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if ((mask >> i & 1) == 1) {
                    lcm = lcm(lcm, coins[i]);
                    if (lcm > m) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                cnt += (Integer.bitCount(mask) & 1) == 1 ? m / lcm : -m / lcm;
            }
        }
        return cnt >= k;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

}
