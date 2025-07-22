package com.sean.leetcode.LeetCode502;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-07-22 10:37
 * @Description https://leetcode.cn/problems/ipo
 * 502. IPO
 * 假设 力扣（LeetCode）即将开始 IPO 。
 * 为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
 * 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。
 * 帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * 给你 n 个项目。
 * 对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * 最初，你的资本为 w 。
 * 当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * 答案保证在 32 位有符号整数范围内。
 * 1 <= k <= 10^5
 * 0 <= w <= 10^9
 * n == profits.length
 * n == capital.length
 * 1 <= n <= 10^5
 * 0 <= profits[i] <= 10^4
 * 0 <= capital[i] <= 10^9
 */
public class Solution {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> Integer.compare(capital[a], capital[b]));
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(profits[b], profits[a]));
        int i = 0;
        do {
            while (i < n && w >= capital[index[i]]) {
                heap.offer(index[i++]);
            }
            if (!heap.isEmpty()) {
                w += profits[heap.poll()];
            } else {
                break;
            }
        } while (--k > 0);
        return w;
    }

}
