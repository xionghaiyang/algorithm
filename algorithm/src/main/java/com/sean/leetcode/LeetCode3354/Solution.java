package com.sean.leetcode.LeetCode3354;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-28 12:32
 * @Description https://leetcode.cn/problems/make-array-elements-equal-to-zero
 * 3354. 使数组元素等于零
 * 给你一个整数数组 nums 。
 * 开始时，选择一个满足 nums[curr] == 0 的起始位置 curr ，并选择一个移动 方向 ：向左或者向右。
 * 此后，你需要重复下面的过程：
 * 如果 curr 超过范围 [0, n - 1] ，过程结束。
 * 如果 nums[curr] == 0 ，沿当前方向继续移动：如果向右移，则 递增 curr ；如果向左移，则 递减 curr 。
 * 如果 nums[curr] > 0:
 * 将 nums[curr] 减 1 。
 * 反转 移动方向（向左变向右，反之亦然）。
 * 沿新方向移动一步。
 * 如果在结束整个过程后，nums 中的所有元素都变为 0 ，则认为选出的初始位置和移动方向 有效 。
 * 返回可能的有效选择方案数目。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * 至少存在一个元素 i 满足 nums[i] == 0 。
 */
public class Solution {

    public int countValidSelections(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int res = 0, pre = 0;
        for (int num : nums) {
            if (num > 0) {
                pre += num;
            } else if (pre * 2 == total) {
                res += 2;
            } else if (Math.abs(pre * 2 - total) == 1) {
                res++;
            }
        }
        return res;
    }

}
