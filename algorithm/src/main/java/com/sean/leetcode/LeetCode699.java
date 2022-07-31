package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode699 {

    public static List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0], right1 = positions[i][0] + positions[i][1] - 1;
            int height = positions[i][1];
            for (int j = 0; j < i; j++) {
                int left2 = positions[j][0], right2 = positions[j][0] + positions[j][1] - 1;
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
