package com.sean.leetcode;

import java.util.Stack;

/**
 * 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class LeetCode232 {

    private static class MyQueue {

        private Stack<Integer> inStack = null;
        private Stack<Integer> outStack = null;

        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()) {
                in2out();
            }
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()) {
                in2out();
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void in2out() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

}
