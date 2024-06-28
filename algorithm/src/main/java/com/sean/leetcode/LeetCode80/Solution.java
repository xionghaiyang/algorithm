package com.sean.leetcode.LeetCode80;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-13 13:49
 * @Description: https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (map.getOrDefault(nums[i], 0) < 2) {
                nums[j++] = nums[i];
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return j;
    }

}
