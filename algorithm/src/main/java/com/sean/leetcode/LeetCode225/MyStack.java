package com.sean.leetcode.LeetCode225;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-03 12:59
 * @Description: https://leetcode.cn/problems/implement-stack-using-queues/description/
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 */
public class MyStack {

    Queue<Integer> out;
    Queue<Integer> in;

    public MyStack() {
        out = new LinkedList<>();
        in = new LinkedList<>();
    }

    public void push(int x) {
        in.offer(x);
        while (!out.isEmpty()) {
            in.offer(out.poll());
        }
        Queue<Integer> temp = out;
        out = in;
        in = temp;
    }

    public int pop() {
        return out.poll();
    }

    public int top() {
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty();
    }

}
