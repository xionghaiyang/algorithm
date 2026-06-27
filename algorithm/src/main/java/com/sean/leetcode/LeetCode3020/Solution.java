package com.sean.leetcode.LeetCode3020;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-27 09:10
 * @Description: https://leetcode.cn/problems/find-the-maximum-number-of-elements-in-subset
 * 3020. 子集中元素的最大数量
 * 给你一个 正整数 数组 nums 。
 * 你需要从数组中选出一个满足下述条件的子集：
 * 你可以将选中的元素放置在一个下标从 0 开始的数组中，并使其遵循以下模式：[x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x]（注意，k 可以是任何 非负 的 2 的幂）。
 * 例如，[2, 4, 16, 4, 2] 和 [3, 9, 3] 都符合这一模式，而 [2, 4, 8, 4, 2] 则不符合。
 * 返回满足这些条件的子集中，元素数量的 最大值 。
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public int maximumLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        Integer cnt1 = map.remove(1);
        int res = cnt1 != null ? (cnt1 - 1) | 1 : 0;
        for (int num : map.keySet()) {
            int ans = 0;
            while (map.getOrDefault(num, 0) >= 2) {
                ans += 2;
                num *= num;
            }
            res = Math.max(res, ans + (map.containsKey(num) ? 1 : -1));
        }
        return res;
    }

}
