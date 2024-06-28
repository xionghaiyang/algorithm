package com.sean.leetcode.LeetCode1040;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-07 08:00
 * @Description: https://leetcode.cn/problems/moving-stones-until-consecutive-ii/
 * 1040. 移动石子直到连续 II
 * 在一个长度 无限 的数轴上，第 i 颗石子的位置为 stones[i]。
 * 如果一颗石子的位置最小/最大，那么该石子被称作 端点石子 。
 * 每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。
 * 值得注意的是，如果石子像 stones = [1,2,5] 这样，你将 无法 移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3），该石子都仍然会是端点石子。
 * 当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
 * 要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves] 。
 */
public class Solution {

    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        if (stones[n - 1] - stones[0] + 1 == 0) {
            return new int[]{0, 0};
        }
        int max = Math.max(stones[n - 2] - stones[0] + 1, stones[n - 1] - stones[1] + 1) - (n - 1);
        int min = n;
        for (int left = 0, right = 0; left < n && right + 1 < n; left++) {
            while (right + 1 < n && stones[right + 1] - stones[left] + 1 <= n) {
                right++;
            }
            if (right - left + 1 == n - 1 && stones[right] - stones[left] + 1 == n - 1) {
                min = Math.min(min, 2);
            } else {
                min = Math.min(min, n - (right - left + 1));
            }
        }
        return new int[]{min, max};
    }

}
