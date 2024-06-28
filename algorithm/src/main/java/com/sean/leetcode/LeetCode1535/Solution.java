package com.sean.leetcode.LeetCode1535;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2024-05-19 12:35
 * @Description https://leetcode.cn/problems/find-the-winner-of-an-array-game/
 * 1535. 找出数组游戏的赢家
 * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。
 * 比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0 ，较小的整数移至数组的末尾。
 * 当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
 * 返回赢得比赛的整数。
 * 题目数据 保证 游戏存在赢家。
 */
public class Solution {

    //超时
    public int getWinner(int[] arr, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            queue.offer(i);
        }
        int cnt = 0, index = 0;
        while (cnt < k) {
            int next = queue.poll();
            if (arr[index] > arr[next]) {
                cnt++;
                queue.offer(next);
            } else {
                queue.offer(index);
                index = next;
                cnt = 1;
            }
        }
        return arr[index];
    }

    public int getWinner1(int[] arr, int k) {
        int prev = Math.max(arr[0], arr[1]);
        if (k == 1) {
            return prev;
        }
        int res = prev, cnt = 1, n = arr.length;
        for (int i = 2; i < n; i++) {
            int curr = arr[i];
            if (prev > curr) {
                cnt++;
                if (cnt == k) {
                    return prev;
                }
            } else {
                prev = curr;
                cnt = 1;
            }
            res = Math.max(res, curr);
        }
        return res;
    }

}
