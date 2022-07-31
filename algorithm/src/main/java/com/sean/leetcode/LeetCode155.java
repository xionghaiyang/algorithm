package com.sean.leetcode;

import java.util.Stack;

public class LeetCode155 {

    static class MinStack {

        Stack<Integer> minStack = null;
        Stack<Integer> stack = null;

        public MinStack() {
            minStack = new Stack<>();
            stack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(val, minStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());//--> 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());//--> 返回 0.
        System.out.println(minStack.getMin());//--> 返回 -2.
    }


}
