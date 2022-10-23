package com.sean.leetcode.LeetCode901;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-21 08:25
 * @Description: https://leetcode.cn/problems/online-stock-span/
 * 901. 股票价格跨度
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 */
public class StockSpanner {

    private Deque<int[]> stack;
    private int index;

    public StockSpanner() {
        stack = new ArrayDeque<>();
        stack.push(new int[]{-1, Integer.MAX_VALUE});
        index = -1;
    }

    public int next(int price) {
        index++;
        while (price >= stack.peek()[1]) {
            stack.pop();
        }
        int res = index - stack.peek()[0];
        stack.push(new int[]{index, price});
        return res;
    }

}
