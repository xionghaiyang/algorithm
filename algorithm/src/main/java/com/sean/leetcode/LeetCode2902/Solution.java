package com.sean.leetcode.LeetCode2902;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-16 12:20
 * @Description: https://leetcode.cn/problems/count-of-sub-multisets-with-bounded-sum
 * 2902. 和带限制的子多重集合的数目
 * 给你一个下标从 0 开始的非负整数数组 nums 和两个整数 l 和 r 。
 * 请你返回 nums 中子多重集合的和在闭区间 [l, r] 之间的 子多重集合的数目 。
 * 由于答案可能很大，请你将答案对 10^9 + 7 取余后返回。
 * 子多重集合 指的是从数组中选出一些元素构成的 无序 集合，每个元素 x 出现的次数可以是 0, 1, ..., occ[x] 次，其中 occ[x] 是元素 x 在数组中的出现次数。
 * 注意：
 * 如果两个子多重集合中的元素排序后一模一样，那么它们两个是相同的 子多重集合 。
 * 空 集合的和是 0 。
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2 * 10^4
 * nums 的和不超过 2 * 10^4 。
 * 0 <= l <= r <= 2 * 10^4
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countSubMultisets(List<Integer> nums, int l, int r) {
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            total += num;
            map.merge(num, 1, Integer::sum);
        }
        if (l > total) {
            return 0;
        }
        r = Math.min(r, total);
        int[] f = new int[r + 1];
        f[0] = map.getOrDefault(0, 0) + 1;
        map.remove(0);
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey(), c = entry.getValue();
            int[] nf = f.clone();
            sum = Math.min(sum + x * c, r);
            for (int j = x; j <= sum; j++) {
                nf[j] = (nf[j] + nf[j - x]) % MOD;
                if (j >= (c + 1) * x) {
                    nf[j] = (nf[j] - f[j - (c + 1) * x] + MOD) % MOD;
                }
            }
            f = nf;
        }
        int res = 0;
        for (int i = l; i <= r; i++) {
            res = (res + f[i]) % MOD;
        }
        return res;
    }

}
