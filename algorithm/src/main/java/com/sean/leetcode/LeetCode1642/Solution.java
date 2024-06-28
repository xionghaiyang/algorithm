package com.sean.leetcode.LeetCode1642;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-19 18:30
 * @Description: https://leetcode.cn/problems/furthest-building-you-can-reach/description/
 * 1642. 可以到达的最远建筑
 * 给你一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
 * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
 * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
 * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
 * 如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
 * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 */
public class Solution {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            int d = heights[i + 1] - heights[i];
            if (d > 0) {
                pq.offer(d);
            }
            if (pq.size() > ladders) {
                bricks -= pq.poll();
            }
            if (bricks < 0) {
                return i;
            }
        }
        return n - 1;
    }

}
