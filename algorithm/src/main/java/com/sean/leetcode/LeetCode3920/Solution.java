package com.sean.leetcode.LeetCode3920;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-30 18:29
 * @Description: https://leetcode.cn/problems/maximize-fixed-points-after-deletions
 * 3920. 删除元素后最大固定点数目
 * 给你一个整数数组 nums。
 * 如果 nums[i] == i，则位置 i 被称为 固定点。
 * 允许你从数组中删除 任意 数量的元素（包括零个）。
 * 在每次删除后，剩余元素 向左移动，并且下标从 0 开始重新分配。
 * 返回一个整数，表示在执行任意次数的删除操作后，可以获得的 最大 固定点数量。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class Solution {

    public int maxFixedPoints(int[] nums) {
        int n = nums.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (i >= x) {
                list.add(new int[]{x, i - x});
            }
        }
        return maxEnvelopes(list);
    }

    private int maxEnvelopes(List<int[]> envelopes) {
        int size = envelopes.size();
        envelopes.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] f = new int[size + 1];
        int len = 0;
        for (int i = 0; i < size; i++) {
            int x = envelopes.get(i)[1];
            if (len == 0 || x >= f[len]) {
                f[++len] = x;
            } else {
                int left = 1, right = len, j = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (f[mid] <= x) {
                        j = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                f[j + 1] = x;
            }
        }
        return len;
    }

}
