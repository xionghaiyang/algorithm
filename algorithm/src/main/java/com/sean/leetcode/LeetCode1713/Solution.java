package com.sean.leetcode.LeetCode1713;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-31 18:48
 * @Description: https://leetcode.cn/problems/minimum-operations-to-make-a-subsequence
 * 1713. 得到子序列的最少操作次数
 * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
 * 每一次操作中，你可以在 arr 的任意位置插入任一整数。
 * 比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,2] 。
 * 你可以在数组最开始或最后面添加整数。
 * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
 * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。
 * 比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
 * 1 <= target.length, arr.length <= 10^5
 * 1 <= target[i], arr[i] <= 10^9
 * target 不包含任何重复元素。
 */
public class Solution {

    public int minOperations(int[] target, int[] arr) {
        int m = target.length, n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                list.add(map.get(arr[i]));
            }
        }
        int size = list.size(), len = 0;
        int[] f = new int[size + 1];
        for (int i = 0; i < size; i++) {
            int x = list.get(i);
            if (len == 0 || x > f[len]) {
                f[++len] = x;
            } else {
                int left = 1, right = len, j = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (f[mid] < x) {
                        j = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                f[j + 1] = x;
            }
        }
        return m - len;
    }

}
