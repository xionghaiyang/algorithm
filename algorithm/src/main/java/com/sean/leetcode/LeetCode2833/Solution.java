package com.sean.leetcode.LeetCode2833;

/**
 * @Author xionghaiyang
 * @Date 2026-04-24 18:38
 * @Description https://leetcode.cn/problems/furthest-point-from-origin
 * 2833. 距离原点最远的点
 * 给你一个长度为 n 的字符串 moves ，该字符串仅由字符 'L'、'R' 和 '_' 组成。
 * 字符串表示你在一条原点为 0 的数轴上的若干次移动。
 * 你的初始位置就在原点（0），第 i 次移动过程中，你可以根据对应字符选择移动方向：
 * 如果 moves[i] = 'L' 或 moves[i] = '_' ，可以选择向左移动一个单位距离
 * 如果 moves[i] = 'R' 或 moves[i] = '_' ，可以选择向右移动一个单位距离
 * 移动 n 次之后，请你找出可以到达的距离原点 最远 的点，并返回 从原点到这一点的距离 。
 * 1 <= moves.length == n <= 50
 * moves 仅由字符 'L'、'R' 和 '_' 组成
 */
public class Solution {

    public int furthestDistanceFromOrigin(String moves) {
        int pos = 0, cnt = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                pos--;
            } else if (c == 'R') {
                pos++;
            } else {
                cnt++;
            }
        }
        return Math.abs(pos) + cnt;
    }

}
