package com.sean.leetcode.LeetCodeInterview1005;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 11:36
 * @Description https://leetcode.cn/problems/sparse-array-search-lcci
 * 面试题 10.05. 稀疏数组搜索
 * 稀疏数组搜索。
 * 有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 * words的长度在[1, 1000000]之间
 */
public class Solution {

    public int findString(String[] words, String s) {
        int n = words.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            while (left < right && words[left].equals("")) {
                left++;
            }
            while (left < right && words[right].equals("")) {
                right--;
            }
            if (left <= right) {
                int mid = left + ((right - left) >> 1);
                int tmp = mid;
                while (tmp < right && words[tmp].equals("")) {
                    tmp++;
                }
                if (words[tmp].compareTo(s) > 0) {
                    right = mid - 1;
                } else if (words[tmp].compareTo(s) < 0) {
                    left = tmp + 1;
                } else {
                    return tmp;
                }
            }
        }
        return -1;
    }

}
