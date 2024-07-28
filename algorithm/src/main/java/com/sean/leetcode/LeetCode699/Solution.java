package com.sean.leetcode.LeetCode699;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-07-28 18:37
 * @Description https://leetcode.cn/problems/falling-squares/
 * 699. 掉落的方块
 * 在二维平面上的 x 轴上，放置着一些方块。
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。
 * 方块沿 y 轴负方向下落，直到着陆到 另一个正方形的顶边 或者是 x 轴上 。
 * 一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。
 * 一旦着陆，它就会固定在原地，无法移动。
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 */
public class Solution {

    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0];
            int right1 = positions[i][0] + positions[i][1] - 1;
            int height = positions[i][1];
            for (int j = 0; j < i; j++) {
                int left2 = positions[j][0];
                int right2 = positions[j][0] + positions[j][1] - 1;
                if (right1 >= left2 && right2 >= left1) {
                    height = Math.max(height, heights.get(j) + positions[i][1]);
                }
            }
            heights.add(height);
        }
        for (int i = 1; i < n; i++) {
            heights.set(i, Math.max(heights.get(i), heights.get(i - 1)));
        }
        return heights;
    }

}
