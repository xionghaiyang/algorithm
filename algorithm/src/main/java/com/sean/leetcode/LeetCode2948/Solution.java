package com.sean.leetcode.LeetCode2948;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-29 06:04
 * @Description: https://leetcode.cn/problems/make-lexicographically-smallest-array-by-swapping-elements
 * 2948. 交换得到字典序最小的数组
 * 给你一个下标从 0 开始的 正整数 数组 nums 和一个 正整数 limit 。
 * 在一次操作中，你可以选择任意两个下标 i 和 j，如果 满足 |nums[i] - nums[j]| <= limit ，则交换 nums[i] 和 nums[j] 。
 * 返回执行任意次操作后能得到的 字典序最小的数组 。
 * 如果在数组 a 和数组 b 第一个不同的位置上，数组 a 中的对应元素比数组 b 中的对应元素的字典序更小，则认为数组 a 就比数组 b 字典序更小。例如，数组 [2,10,3] 比数组 [10,2,3] 字典序更小，下标 0 处是两个数组第一个不同的位置，且 2 < 10 。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= limit <= 10^9
 */
public class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] pos = new Integer[n];
        Arrays.setAll(pos, i -> i);
        Arrays.sort(pos, (i, j) -> nums[i] - nums[j]);
        List<Integer> groups = new ArrayList<>();
        int[] belong = new int[n];
        for (int i = 0; i < n; i++) {
            int p = pos[i];
            if (i == 0 || nums[p] - nums[pos[i - 1]] > limit) {
                groups.add(i);
            }
            belong[p] = groups.size() - 1;
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int gid = belong[i];
            int curIndex = groups.get(gid);
            res[i] = nums[pos[curIndex]];
            groups.set(gid, curIndex + 1);
        }
        return res;
    }

}
