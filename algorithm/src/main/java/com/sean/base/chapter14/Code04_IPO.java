package com.sean.base.chapter14;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-31 21:16
 * @Description: TODO
 */
public class Code04_IPO {

    public class Program {
        public int profit;
        public int cost;

        public Program(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }

    //最多k个项目
    //w是初始资金
    //profits[] 和capital[]一定等长
    //返回最终最大的资金
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Program> minCostHeap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        PriorityQueue<Program> maxProfitHeap = new PriorityQueue<>((a, b) -> b.profit - a.profit);
        for (int i = 0; i < profits.length; i++) {
            minCostHeap.add(new Program(profits[i], capital[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= w) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return w;
            }
            w += maxProfitHeap.poll().profit;
        }
        return w;
    }

}
