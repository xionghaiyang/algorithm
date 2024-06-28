package com.sean.leetcode.LeetCode1090;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-23 08:06
 * @Description: https://leetcode.cn/problems/largest-values-from-labels/
 * 1090. 受标签影响的最大值
 * 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，
 * 第 i 个元素的值和标签分别是 values[i] 和 labels[i]。
 * 还会给出两个整数 numWanted 和 useLimit 。
 * 从 n 个元素中选择一个子集 s :
 * 子集 s 的大小 小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。
 * 一个子集的 分数 是该子集的值之和。
 * 返回子集 s 的最大 分数 。
 */
public class Solution {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> values[b] - values[a]);
        int res = 0;
        int cnt = 0;
        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (cnt < numWanted && i < n) {
            if (map.getOrDefault(labels[index[i]], 0) < useLimit) {
                res += values[index[i]];
                map.put(labels[index[i]], map.getOrDefault(labels[index[i]], 0) + 1);
                cnt++;
            }
            i++;
        }
        return res;
    }

}
