package com.sean.leetcode.LeetCode155;

/**
 * @Author xionghaiyang
 * @Date 2025-06-16 08:59
 * @Description https://leetcode.cn/problems/min-stack
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * -2^31 <= val <= 2^31 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 */
public class MinStack {

    private Node head;

    private class Node {
        int value;
        int min;
        Node next;

        public Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    public MinStack() {
        head = null;
    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val);
        } else {
            int min = Math.min(val, head.min);
            Node node = new Node(val, min);
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        if (head != null) {
            return head.value;
        }
        throw new IllegalStateException("Stack is empty");
    }

    public int getMin() {
        if (head != null) {
            return head.min;
        }
        throw new IllegalStateException("Stack is empty");
    }

}
