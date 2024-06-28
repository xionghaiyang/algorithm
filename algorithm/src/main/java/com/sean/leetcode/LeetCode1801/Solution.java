package com.sean.leetcode.LeetCode1801;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-03 10:18
 * @Description: https://leetcode.cn/problems/number-of-orders-in-the-backlog/
 * 1801. 积压订单中的订单总数
 * 给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei]
 * 表示有 amounti 笔类型为 orderTypei 、价格为 pricei 的订单。
 * 订单类型 orderTypei 可以分为两种：
 * 0 表示这是一批采购订单 buy
 * 1 表示这是一批销售订单 sell
 * 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。
 * 对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。
 * 存在由未执行订单组成的 积压订单 。
 * 积压订单最初是空的。提交订单时，会发生以下情况：
 * 如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。
 * 如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。
 * 否则，采购订单 buy 将会添加到积压订单中。
 * 反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。
 * 如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。
 * 否则，销售订单 sell 将会添加到积压订单中。
 * 输入所有订单后，返回积压订单中的 订单总数 。
 * 由于数字可能很大，所以需要返回对 10^9 + 7 取余的结果。
 */
public class Solution {

    public int getNumberOfBacklogOrders(int[][] orders) {
        int mod = 1000000007;
        PriorityQueue<int[]> sellOrders = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> buyOrders = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int orderType = order[2];
            if (orderType == 0) {
                while (amount > 0 && !sellOrders.isEmpty() && sellOrders.peek()[0] <= price) {
                    int[] sellOrder = sellOrders.poll();
                    int sellAmount = Math.min(amount, sellOrder[1]);
                    amount -= sellAmount;
                    sellOrder[1] -= sellAmount;
                    if (sellOrder[1] > 0) {
                        sellOrders.offer(sellOrder);
                    }
                }
                if (amount > 0) {
                    buyOrders.offer(new int[]{price, amount});
                }
            } else {
                while (amount > 0 && !buyOrders.isEmpty() && buyOrders.peek()[0] >= price) {
                    int[] buyOrder = buyOrders.poll();
                    int buyAmount = Math.min(amount, buyOrder[1]);
                    amount -= buyAmount;
                    buyOrder[1] -= buyAmount;
                    if (buyOrder[1] > 0) {
                        buyOrders.offer(buyOrder);
                    }
                }
                if (amount > 0) {
                    sellOrders.offer(new int[]{price, amount});
                }
            }
        }
        int res = 0;
        for (PriorityQueue<int[]> heap : Arrays.asList(buyOrders, sellOrders)) {
            while (!heap.isEmpty()) {
                int[] order = heap.poll();
                res = (res + order[1]) % mod;
            }
        }
        return res;
    }

}
