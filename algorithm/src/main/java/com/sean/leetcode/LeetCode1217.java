package com.sean.leetcode;

public class LeetCode1217 {

    public int minCostToMoveChips(int[] position) {
        if (position == null || position.length < 2) {
            return 0;
        }
        int evenCunt = 0, oddCount = 0;
        for (int i = 0; i < position.length; i++) {
            if ((position[i] & 1) == 0) {
                evenCunt++;
            } else {
                oddCount++;
            }
        }
        return Math.min(evenCunt, oddCount);
    }

}
