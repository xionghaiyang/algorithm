package com.sean.leetcode.LeetCodeInterview1708;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 11:34
 * @Description https://leetcode.cn/problems/circus-tower-lcci
 * 面试题 17.08. 马戏团人塔
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 * height.length == weight.length <= 10000
 */
public class Solution {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        int n = height.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (a, b) -> height[a] != height[b] ? height[a] - height[b] : weight[b] - weight[a]);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = weight[indexes[i]];
        }
        return lengthOfLIS(nums);
    }

    private int lengthOfLIS(int[] nums) {
        //长度为i+1的递增子序列的最小可能结尾数字
        //tails一定是严格递增的
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            //找到第一个大于等于num的位置替换
            int left = 0, right = size;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == size) {
                size++;
            }
        }
        return size;
    }

}
