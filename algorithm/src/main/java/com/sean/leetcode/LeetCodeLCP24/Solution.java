package com.sean.leetcode.LeetCodeLCP24;

import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-01 12:43
 * @Description: https://leetcode.cn/problems/5TxKeK/
 * LCP 24. 数字游戏
 * 小扣在秋日市集入口处发现了一个数字游戏。
 * 主办方共有 N 个计数器，计数器编号为 0 ~ N-1。
 * 每个计数器上分别显示了一个数字，小扣按计数器编号升序将所显示的数字记于数组 nums。
 * 每个计数器上有两个按钮，分别可以实现将显示数字加一或减一。
 * 小扣每一次操作可以选择一个计数器，按下加一或减一按钮。
 * 主办方请小扣回答出一个长度为 N 的数组，第 i 个元素(0 <= i < N)表示将 0~i 号计数器 初始 所示数字操作成满足所有条件 nums[a]+1 == nums[a+1],(0 <= a < i) 的最小操作数。
 * 回答正确方可进入秋日市集。
 * 由于答案可能很大，请将每个最小操作数对 1,000,000,007 取余。
 */
public class Solution {

    static final int MOD = 1_000_000_007;

    public int[] numsGame(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        //优先队列lower保存的任一元素都小于等于优先队列upper保存的仁义元素
        PriorityQueue<Integer> lower = new PriorityQueue<>((a, b) -> b - a);
        //优先队列lower的元素数目与优先队列upper的元素数目满足:upper<=lower<=upper+1
        PriorityQueue<Integer> upper = new PriorityQueue<>((a, b) -> a - b);
        long lowerSum = 0, upperSum = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i] - i;
            if (lower.isEmpty() || lower.peek() >= x) {
                lowerSum += x;
                lower.offer(x);
                if (lower.size() > upper.size() + 1) {
                    upperSum += lower.peek();
                    upper.offer(lower.peek());
                    lowerSum -= lower.peek();
                    lower.poll();
                }
            } else {
                upperSum += x;
                upper.offer(x);
                if (lower.size() < upper.size()) {
                    lowerSum += upper.peek();
                    lower.offer(upper.peek());
                    upperSum -= upper.peek();
                    upper.poll();
                }
            }
            if ((i + 1) % 2 == 0) {
                res[i] = (int) ((upperSum - lowerSum) % MOD);
            } else {
                res[i] = (int) ((upperSum - lowerSum + lower.peek()) % MOD);
            }
        }
        return res;
    }

}
