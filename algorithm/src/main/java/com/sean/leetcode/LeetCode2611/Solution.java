package com.sean.leetcode.LeetCode2611;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-07 08:14
 * @Description: https://leetcode.cn/problems/mice-and-cheese/
 * 2611. 老鼠和奶酪
 * 有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
 * 下标为 i 处的奶酪被吃掉的得分为：
 * 如果第一只老鼠吃掉，则得分为 reward1[i] 。
 * 如果第二只老鼠吃掉，则得分为 reward2[i] 。
 * 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。
 * 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
 */
public class Solution {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int res = 0;
        for (int reward : reward2) {
            res += reward;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        int n = reward1.length;
        for (int i = 0; i < k && i < n; i++) {
            pq.offer(reward1[i] - reward2[i]);
        }
        for (int i = k; i < n; i++) {
            pq.offer(reward1[i] - reward2[i]);
            pq.poll();
        }
        while (!pq.isEmpty()) {
            res += pq.poll();
        }
        return res;
    }

}
