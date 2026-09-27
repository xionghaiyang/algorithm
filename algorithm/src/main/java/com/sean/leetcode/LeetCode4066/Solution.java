package com.sean.leetcode.LeetCode4066;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-27 17:41
 * @Description: https://leetcode.cn/problems/maximum-equal-adjacent-pairs-after-at-most-one-replacement
 * 4066. 至多一次替换后的最大相邻相等元素对数
 * 给你一个 下标从 1 开始 的整数数组 nums。
 * 你可以选择两个 不同 的值 x 和 y，并 最多 执行一次以下操作：
 * 将 nums 中所有值为 x 的元素替换为 y。
 * 返回执行操作后，相邻且相等的元素对数量的 最大值 。
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public int maxEqualAdjacentPairs(int[] nums) {
        int n = nums.length;
        int base = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 1; i < n; i++) {
            int x = nums[i - 1], y = nums[i];
            if (x == y) {
                base++;
            } else {
                if (x > y) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                long key = (long) x << 32 | y;
                map.merge(key, 1, Integer::sum);
            }
        }
        int maxCnt = map.isEmpty() ? 0 : Collections.max(map.values());
        return base + maxCnt;
    }

}
