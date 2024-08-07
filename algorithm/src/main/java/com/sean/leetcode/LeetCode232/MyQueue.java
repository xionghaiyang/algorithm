package com.sean.leetcode.LeetCode232;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-04 13:07
 * @Description: https://leetcode.cn/problems/implement-queue-using-stacks/
 * 232. 用栈实现队列
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size,和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。
 * 你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 */
public class MyQueue {

    private Deque<Integer> in;
    private Deque<Integer> out;

    public MyQueue() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        if (out.isEmpty()) {
            in2out();
        }
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty()) {
            in2out();
        }
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void in2out() {
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
    }

}
