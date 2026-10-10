package com.sean.leetcode.LeetCode3685;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-10 09:37
 * @Description: https://leetcode.cn/problems/subsequence-sum-after-capping-elements
 * 3685. 含上限元素的子序列和
 * 给你一个大小为 n 的整数数组 nums 和一个正整数 k。
 * 通过将每个元素 nums[i] 替换为 min(nums[i], x)，可以得到一个由值 x 限制（capped）的数组。
 * 对于从 1 到 n 的每个整数 x，确定是否可以从由 x 限制的数组中选择一个 子序列，使所选元素的和 恰好 为 k。
 * 返回一个下标从 0 开始的布尔数组 answer，其大小为 n，其中 answer[i] 为 true 表示当 x = i + 1 时可以选出满足要求的子序列；否则为 false。
 * 子序列 是一个从数组中通过删除一些或不删除任何元素（且不改变剩余元素顺序）派生出来的 非空 数组。
 * 1 <= n == nums.length <= 4000
 * 1 <= nums[i] <= n
 * 1 <= k <= 4000
 */
public class Solution {

    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] res = new boolean[n];
        boolean[] f = new boolean[k + 1];
        f[0] = true;
        for (int x = 1, i = 0; x <= n; x++) {
            while (i < n && nums[i] == x) {
                for (int j = k; j >= nums[i]; j--) {
                    f[j] = f[j] | f[j - nums[i]];
                }
                i++;
            }
            for (int j = 0; j <= Math.min(n - i, k / x); j++) {
                if (f[k - j * x]) {
                    res[x - 1] = true;
                    break;
                }
            }
        }
        return res;
    }

    public boolean[] subsequenceSumAfterCapping1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] res = new boolean[n];
        BigInteger f = BigInteger.ONE;
        BigInteger u = BigInteger.ONE.shiftLeft(k + 1).subtract(BigInteger.ONE);//(1<<(k+1)) - 1
        for (int x = 1, i = 0; x <= n; x++) {
            while (i < n && nums[i] == x) {
                f = f.or(f.shiftLeft(nums[i])).and(u);
                i++;
            }
            for (int j = 0; j <= Math.min(n - i, k / x); j++) {
                if (f.testBit(k - j * x)) {
                    res[x - 1] = true;
                    break;
                }
            }
        }
        return res;
    }

}
