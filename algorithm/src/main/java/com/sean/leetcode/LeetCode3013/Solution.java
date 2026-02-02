package com.sean.leetcode.LeetCode3013;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author xionghaiyang
 * @Date 2026-02-02 07:17
 * @Description https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-ii
 * 3013. 将数组分成最小总代价的子数组 II
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和两个 正 整数 k 和 dist 。
 * 一个数组的 代价 是数组中的 第一个 元素。
 * 比方说，[1,2,3] 的代价为 1 ，[3,4,1] 的代价为 3 。
 * 你需要将 nums 分割成 k 个 连续且互不相交 的子数组，满足 第二 个子数组与第 k 个子数组中第一个元素的下标距离 不超过 dist 。
 * 换句话说，如果你将 nums 分割成子数组 nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)] ，那么它需要满足 ik-1 - i1 <= dist 。
 * 请你返回这些子数组的 最小 总代价。
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 3 <= k <= n
 * k - 2 <= dist <= n - 2
 */
public class Solution {

    private long sumL;
    private int sizeL;
    private final TreeMap<Integer, Integer> L = new TreeMap<>();
    private final TreeMap<Integer, Integer> R = new TreeMap<>();

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        k--;
        sumL = nums[0];
        for (int i = 1; i < dist + 2; i++) {
            sumL += nums[i];
            L.merge(nums[i], 1, Integer::sum);
        }
        sizeL = dist + 1;
        while (sizeL > k) {
            l2r();
        }
        long res = sumL;
        for (int i = dist + 2; i < n; i++) {
            int out = nums[i - dist - 1];
            if (L.containsKey(out)) {
                sumL -= out;
                sizeL--;
                removeOne(L, out);
            } else {
                removeOne(R, out);
            }
            int in = nums[i];
            if (in < L.lastKey()) {
                sumL += in;
                sizeL++;
                L.merge(in, 1, Integer::sum);
            } else {
                R.merge(in, 1, Integer::sum);
            }
            if (sizeL == k - 1) {
                r2l();
            } else if (sizeL == k + 1) {
                l2r();
            }
            res = Math.min(res, sumL);
        }
        return res;
    }

    private void l2r() {
        int x = L.lastKey();
        removeOne(L, x);
        sumL -= x;
        sizeL--;
        R.merge(x, 1, Integer::sum);
    }

    private void r2l() {
        int x = R.firstKey();
        removeOne(R, x);
        sumL += x;
        sizeL++;
        L.merge(x, 1, Integer::sum);
    }

    private void removeOne(Map<Integer, Integer> map, int x) {
        int cnt = map.get(x);
        if (cnt > 1) {
            map.put(x, cnt - 1);
        } else {
            map.remove(x);
        }
    }

}
