package com.sean.leetcode.LeetCode2215;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 15:20
 * @Description: https://leetcode.cn/problems/find-the-difference-of-two-arrays/?envType=study-plan-v2&envId=leetcode-75
 * 2215. 找出两数组的不同
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，请你返回一个长度为 2 的列表 answer ，其中：
 * answer[0] 是 nums1 中所有 不 存在于 nums2 中的 不同 整数组成的列表。
 * answer[1] 是 nums2 中所有 不 存在于 nums1 中的 不同 整数组成的列表。
 * 注意：列表中的整数可以按 任意 顺序返回。
 */
public class Solution {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> answer0 = new ArrayList<>();
        List<Integer> answer1 = new ArrayList<>();
        answer.add(answer0);
        answer.add(answer1);
        for (int num : set1) {
            if (!set2.contains(num)) {
                answer0.add(num);
            }
        }
        for (int num : set2) {
            if (!set1.contains(num)) {
                answer1.add(num);
            }
        }
        return answer;
    }

}
