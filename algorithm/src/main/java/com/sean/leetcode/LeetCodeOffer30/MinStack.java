package com.sean.leetcode.LeetCodeOffer30;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 21:49
 * @Description: https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (minStack.peek().equals(stack.pop())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
