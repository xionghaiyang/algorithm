package com.sean.leetcode.LeetCode3489;

import java.math.BigInteger;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-10 08:18
 * @Description: https://leetcode.cn/problems/zero-array-transformation-iv
 * 3489. 零数组变换 IV
 * 给你一个长度为 n 的整数数组 nums 和一个二维数组 queries ，其中 queries[i] = [li, ri, vali]。
 * 每个 queries[i] 表示以下操作在 nums 上执行：
 * 从数组 nums 中选择范围 [li, ri] 内的一个下标子集。
 * 将每个选中下标处的值减去 正好 vali。
 * 零数组 是指所有元素都等于 0 的数组。
 * 返回使得经过前 k 个查询（按顺序执行）后，nums 转变为 零数组 的最小可能 非负 值 k。
 * 如果不存在这样的 k，返回 -1。
 * 数组的 子集 是指从数组中选择的一些元素（可能为空）。
 * 1 <= nums.length <= 10
 * 0 <= nums[i] <= 1000
 * 1 <= queries.length <= 1000
 * queries[i] = [li, ri, vali]
 * 0 <= li <= ri < nums.length
 * 1 <= vali <= 10
 */
public class Solution {

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x == 0) {
                continue;
            }
            boolean[] f = new boolean[x + 1];
            f[0] = true;
            for (int k = 0; k < m; k++) {
                int l = queries[k][0], r = queries[k][1], val = queries[k][2];
                if (i < l || i > r) {
                    continue;
                }
                for (int j = x; j >= val; j--) {
                    f[j] = f[j] || f[j - val];
                }
                if (f[x]) {
                    res = Math.max(res, k + 1);
                    break;
                }
            }
            if (!f[x]) {
                return -1;
            }
        }
        return res;
    }

    public int minZeroArray1(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x == 0) {
                continue;
            }
            BigInteger f = BigInteger.ONE;
            for (int k = 0; k < m; k++) {
                int l = queries[k][0], r = queries[k][1], val = queries[k][2];
                if (i < l || i > r) {
                    continue;
                }
                f = f.or(f.shiftLeft(val));
                if (f.testBit(x)) {
                    res = Math.max(res, k + 1);
                    break;
                }
            }
            if (!f.testBit(x)) {
                return -1;
            }
        }
        return res;
    }

}
