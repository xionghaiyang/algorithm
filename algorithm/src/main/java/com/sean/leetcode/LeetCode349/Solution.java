package com.sean.leetcode.LeetCode349;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-27 19:46
 * @Description: https://leetcode.cn/problems/intersection-of-two-arrays/
 * 349. 两个数组的交集
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。
 * 输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 */
public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }
        int[] res = new int[set2.size()];
        int index = 0;
        for (int num : set2) {
            res[index++] = num;
        }
        return res;
    }

}
