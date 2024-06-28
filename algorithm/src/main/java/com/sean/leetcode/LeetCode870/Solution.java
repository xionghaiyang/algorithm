package com.sean.leetcode.LeetCode870;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-08 08:32
 * @Description: https://leetcode.cn/problems/advantage-shuffle/
 * 870. 优势洗牌
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 */
public class Solution {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] index1 = new Integer[n];
        Integer[] index2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            index1[i] = i;
            index2[i] = i;
        }
        Arrays.sort(index1, (i, j) -> nums1[i] - nums1[j]);
        Arrays.sort(index2, (i, j) -> nums2[i] - nums2[j]);
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums1[index1[i]] > nums2[index2[left]]) {
                res[index2[left]] = nums1[index1[i]];
                left++;
            } else {
                res[index2[right]] = nums1[index1[i]];
                right--;
            }
        }
        return res;
    }

}
