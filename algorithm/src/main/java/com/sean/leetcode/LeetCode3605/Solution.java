package com.sean.leetcode.LeetCode3605;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-06 18:22
 * @Description https://leetcode.cn/problems/minimum-stability-factor-of-array
 * 3605. 数组的最小稳定性因子
 * 给你一个整数数组 nums 和一个整数 maxC。
 * 如果一个 子数组 的所有元素的最大公因数（简称 HCF） 大于或等于 2，则称该子数组是稳定的。
 * 一个数组的 稳定性因子 定义为其 最长 稳定子数组的长度。
 * 你 最多 可以修改数组中的 maxC 个元素为任意整数。
 * 在最多 maxC 次修改后，返回数组的 最小 可能稳定性因子。
 * 如果没有稳定的子数组，则返回 0。
 * 注意:
 * 子数组 是数组中连续的元素序列。
 * 数组的 最大公因数（HCF）是能同时整除数组中所有元素的最大整数。
 * 如果长度为 1 的 子数组 中唯一元素大于等于 2，那么它是稳定的，因为 HCF([x]) = x。
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= maxC <= n
 */
public class Solution {

    public int minStable(int[] nums, int maxC) {
        int left = -1, right = nums.length / (maxC + 1);
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, maxC, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int maxC, int upper) {
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            //计算以i为右端点的子数组gcd
            for (int[] interval : intervals) {
                interval[0] = gcd(interval[0], x);
            }
            //nums[i]单独一个数作为子数组
            intervals.add(new int[]{x, i});
            //去重（合并gcd相同的区间）
            int index = 1;
            for (int j = 1; j < intervals.size(); j++) {
                if (intervals.get(j)[0] != intervals.get(j - 1)[0]) {
                    intervals.set(index, intervals.get(j));
                    index++;
                }
            }
            intervals.subList(index, intervals.size()).clear();
            //intervals的性质：越靠左，gcd越小
            if (intervals.get(0)[0] == 1) {
                intervals.remove(0);
            }
            //intervals[0]的gcd>=2且最长，取其区间左端点作为子数组的最小左端点
            if (!intervals.isEmpty() && i - intervals.get(0)[1] + 1 > upper) {
                if (maxC == 0) {
                    return false;
                }
                maxC--;
                //修改后gcd均为1，直接清空
                intervals.clear();
            }
        }
        return true;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
