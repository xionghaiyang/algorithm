package com.sean.leetcode.LeetCode3624;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 18:16
 * @Description https://leetcode.cn/problems/number-of-integers-with-popcount-depth-equal-to-k-ii
 * 3624. 位计数深度为 K 的整数数目 II
 * 给你一个整数数组 nums。
 * 对于任意正整数 x，定义以下序列：
 * p0 = x
 * pi+1 = popcount(pi)，对于所有 i >= 0，其中 popcount(y) 表示整数 y 的二进制表示中 1 的个数。
 * 这个序列最终会收敛到值 1。
 * popcount-depth（位计数深度）定义为满足 pd = 1 的最小整数 d >= 0。
 * 例如，当 x = 7（二进制表示为 "111"）时，该序列为：7 → 3 → 2 → 1，因此 7 的 popcount-depth 为 3。
 * 此外，给定一个二维整数数组 queries，其中每个 queries[i] 可以是以下两种类型之一：
 * [1, l, r, k] - 计算在区间 [l, r] 中，满足 nums[j] 的 popcount-depth 等于 k 的索引 j 的数量。
 * [2, idx, val] - 将 nums[idx] 更新为 val。
 * 返回一个整数数组 answer，其中 answer[i] 表示第 i 个类型为 [1, l, r, k] 的查询的结果。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^15
 * 1 <= queries.length <= 10^5
 * queries[i].length == 3 或 4
 * queries[i] == [1, l, r, k] 或
 * queries[i] == [2, idx, val]
 * 0 <= l <= r <= n - 1
 * 0 <= k <= 5
 * 0 <= idx <= n - 1
 * 1 <= val <= 10^15
 */
public class Solution {

    public class FenwickTree {
        private int[] tree;

        public FenwickTree(int n) {
            tree = new int[n + 1];
        }

        //nums[i]增加val
        public void update(int i, int val) {
            while (i < tree.length) {
                tree[i] += val;
                i += i & -i;
            }
        }

        //求区间和nums[l]+...+nums[r]
        public int query(int l, int r) {
            return pre(r) - pre(l - 1);
        }

        //求前缀和nums[1]+...+nums[i]
        private int pre(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i &= i - 1;
            }
            return res;
        }
    }

    private static final int[] popDepthList = new int[51];

    static {
        for (int i = 2; i < popDepthList.length; i++) {
            popDepthList[i] = popDepthList[Integer.bitCount(i)] + 1;
        }
    }

    public int[] popcountDepth(long[] nums, long[][] queries) {
        int n = nums.length;
        FenwickTree[] f = new FenwickTree[6];
        Arrays.setAll(f, i -> new FenwickTree(n + 1));
        for (int i = 0; i < n; i++) {
            update(i, nums[i], 1, f);
        }
        int size = 0;
        for (long[] query : queries) {
            size += query[0] == 1 ? 1 : 0;
        }
        int[] res = new int[size];
        int idx = 0;
        for (long[] query : queries) {
            if (query[0] == 1) {
                res[idx++] = f[(int) query[3]].query((int) query[1] + 1, (int) query[2] + 1);
            } else {
                int i = (int) query[1];
                update(i, nums[i], -1, f);
                nums[i] = query[2];
                update(i, nums[i], 1, f);
            }
        }
        return res;
    }

    private void update(int i, long x, int delta, FenwickTree[] f) {
        int d = popDepth(x);
        f[d].update(i + 1, delta);
    }

    private int popDepth(long x) {
        return x == 1 ? 0 : popDepthList[Long.bitCount(x)] + 1;
    }

}
