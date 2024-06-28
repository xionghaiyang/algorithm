package com.sean.leetcode.LeetCode857;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2024-05-02 12:20
 * @Description https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
 * 857. 雇佣 K 名工人的最低成本
 * 有 n 名工人。
 * 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
 * 现在我们想雇佣 k 名工人组成一个工资组。
 * 在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。
 * 在实际答案的 10-5 以内的答案将被接受。
 */
public class Solution {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] h = new Integer[n];
        for (int i = 0; i < n; i++) {
            h[i] = i;
        }
        Arrays.sort(h, (a, b) -> quality[b] * wage[a] - quality[a] * wage[b]);
        double res = 1e9;
        double totalq = 0.0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            totalq += quality[h[i]];
            pq.offer(quality[h[i]]);
        }
        for (int i = k - 1; i < n; i++) {
            int index = h[i];
            totalq += quality[index];
            pq.offer(quality[index]);
            double totalc = ((double) wage[index] / quality[index]) * totalq;
            res = Math.min(res, totalc);
            totalq -= pq.poll();
        }
        return res;
    }

}
