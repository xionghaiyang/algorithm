package com.sean.leetcode.LeetCode2826;

import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-27 18:43
 * @Description: https://leetcode.cn/problems/sorting-three-groups
 * 2826. 将三个组排序
 * 给你一个整数数组 nums 。
 * nums 的每个元素是 1，2 或 3。
 * 在每次操作中，你可以删除 nums 中的一个元素。
 * 返回使 nums 成为 非递减 顺序所需操作数的 最小值。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 3
 */
public class Solution {

    public int minimumOperations(List<Integer> nums) {
        int size = nums.size(), len = 1;
        int[] f = new int[size + 1];
        f[len] = nums.get(0);
        for (int i = 1; i < size; i++) {
            if (nums.get(i) >= f[len]) {
                f[++len] = nums.get(i);
            } else {
                int left = 1, right = len, j = 0;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (f[mid] < nums.get(i)) {
                        j = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                f[j + 1] = nums.get(i);
            }
        }
        return size - len;
    }

    public int minimumOperations1(List<Integer> nums) {
        int[] f = new int[4];
        for (int x : nums) {
            f[x]++;
            f[2] = Math.max(f[2], f[1]);
            f[3] = Math.max(f[3], f[2]);
        }
        return nums.size() - f[3];
    }

}
