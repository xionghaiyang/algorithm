package com.sean.leetcode.LeetCode275;

/**
 * @Author xionghaiyang
 * @Date 2026-04-06 12:25
 * @Description https://leetcode.cn/problems/h-index-ii
 * 275. H 指数 II
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 非降序排列 。
 * 计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）至少 有 h 篇论文分别被引用了至少 h 次。
 * 请你设计并实现对数时间复杂度的算法解决此问题。
 * n == citations.length
 * 1 <= n <= 10^5
 * 0 <= citations[i] <= 1000
 * citations 按 升序排列
 */
public class Solution {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1, res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (citations[mid] >= n - mid) {
                res = n - mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
