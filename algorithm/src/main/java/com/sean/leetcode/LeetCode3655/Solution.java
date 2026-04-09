package com.sean.leetcode.LeetCode3655;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-09 06:35
 * @Description https://leetcode.cn/problems/xor-after-range-multiplication-queries-ii
 * 3655. 区间乘法查询后的异或 II
 * 给你一个长度为 n 的整数数组 nums 和一个大小为 q 的二维整数数组 queries，其中 queries[i] = [li, ri, ki, vi]。
 * 对于每个查询，需要按以下步骤依次执行操作：
 * 设定 idx = li。
 * 当 idx <= ri 时：
 * 更新：nums[idx] = (nums[idx] * vi) % (10^9 + 7)。
 * 将 idx += ki。
 * 在处理完所有查询后，返回数组 nums 中所有元素的 按位异或 结果。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= q == queries.length <= 10^5
 * queries[i] = [li, ri, ki, vi]
 * 0 <= li <= ri < n
 * 1 <= ki <= n
 * 1 <= vi <= 10^5
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = (int) Math.sqrt(queries.length);
        int[][] diff = new int[m][];
        for (int[] query : queries) {
            int l = query[0], r = query[1], k = query[2];
            long v = query[3];
            if (k < m) {
                if (diff[k] == null) {
                    diff[k] = new int[n + k];
                    Arrays.fill(diff[k], 1);
                }
                diff[k][l] = (int) (diff[k][l] * v % MOD);
                r = r - (r - l) % k + k;
                diff[k][r] = (int) (diff[k][r] * pow(v, MOD - 2) % MOD);
            } else {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((long) nums[i] * v % MOD);
                }
            }
        }
        for (int k = 0; k < m; k++) {
            if (diff[k] == null) {
                continue;
            }
            for (int start = 0; start < k; start++) {
                long mul = 1;
                for (int i = start; i < n; i += k) {
                    mul = mul * diff[k][i] % MOD;
                    nums[i] = (int) (nums[i] * mul % MOD);
                }
            }
        }
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    //x^n
    private long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

}
