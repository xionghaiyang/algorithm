package com.sean.leetcode.LeetCode2860;

import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-09-04 15:16
 * @Description https://leetcode.cn/problems/happy-students/
 * 2860. 让所有学生保持开心的分组方法数
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，其中 n 是班级中学生的总数。
 * 班主任希望能够在让所有学生保持开心的情况下选出一组学生：
 * 如果能够满足下述两个条件之一，则认为第 i 位学生将会保持开心：
 * 这位学生被选中，并且被选中的学生人数 严格大于 nums[i] 。
 * 这位学生没有被选中，并且被选中的学生人数 严格小于 nums[i] 。
 * 返回能够满足让所有学生保持开心的分组方法的数目。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 */
public class Solution {

    public int countWays(List<Integer> nums) {
        int[] arr = nums.stream().mapToInt(i -> i).toArray();
        Arrays.sort(arr);
        int res = arr[0] > 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < i && i < arr[i]) {
                res++;
            }
        }
        return res + 1;
    }

}
