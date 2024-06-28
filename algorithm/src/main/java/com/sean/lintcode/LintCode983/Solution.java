package com.sean.lintcode.LintCode983;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-10 11:52
 * @Description: https://www.lintcode.com/problem/983/?showListFe=true&page=1&pageSize=50
 * 983 · 棒球游戏
 * 描述
 * 现在你是棒球比赛分记录员。
 * 给定一个字符串数组，每一个字符串可以是以下4种中的其中一个：
 * 整数 (一个回合的分数): 直接表示这回合你得到的分数。
 * "+" (一个回合的分数): 表示这回合你获得的分数为前两个 有效 分数之和。
 * "D" (一个回合的分数): 表示这回合你得到的分数为你上一次获得的有效分数的两倍。
 * "C" (一种操作，而非一个回合的分数): 表示你上回合的有效分数是无效的，需要移除。
 * 每一轮的操作都是永久性的，可能会影响之前和之后的一轮。
 * 你需要返回在所有回合中获得总分数。
 */
public class Solution {

    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : ops) {
            if ("+".equals(s)) {
                int lastPont = stack.pop();
                int point = lastPont + stack.peek();
                stack.push(lastPont);
                stack.push(point);
            } else if ("D".equals(s)) {
                stack.push(stack.peek() * 2);
            } else if ("C".equals(s)) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

}
