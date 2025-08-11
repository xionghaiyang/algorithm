package com.sean.leetcode.LeetCode2438;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-05-07 10:52
 * @Description https://leetcode.cn/problems/range-product-queries-of-powers
 * 2438. 二的幂数组中查询范围内的乘积
 * 给你一个正整数 n ，你需要找到一个下标从 0 开始的数组 powers ，它包含 最少 数目的 2 的幂，且它们的和为 n 。
 * powers 数组是 非递减 顺序的。
 * 根据前面描述，构造 powers 数组的方法是唯一的。
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] ，其中 queries[i] 表示请你求出满足 lefti <= j <= righti 的所有 powers[j] 的乘积。
 * 请你返回一个数组 answers ，长度与 queries 的长度相同，其中 answers[i]是第 i 个查询的答案。
 * 由于查询的结果可能非常大，请你将每个 answers[i] 都对 10^9 + 7 取余 。
 * 1 <= n <= 10^9
 * 1 <= queries.length <= 10^5
 * 0 <= starti <= endi < powers.length
 */
public class Solution {

    public int[] productQueries(int n, int[][] queries) {
        final int MOD = 1_000_000_007;
        List<Integer> powers = new ArrayList<>();
        while (n > 0) {
            powers.add(n & -n);
            n &= n - 1;
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int left = queries[i][0], right = queries[i][1];
            long ans = 1;
            for (int j = left; j <= right; j++) {
                ans = ans * powers.get(j) % MOD;
            }
            res[i] = (int) ans;
        }
        return res;
    }

}
