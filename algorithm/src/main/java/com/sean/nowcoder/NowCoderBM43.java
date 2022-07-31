package com.sean.nowcoder;

import java.util.Stack;

public class NowCoderBM43 {

    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    public void push(int node) {
        stack.push(node);
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else {
            int min = minStack.peek();
            int newMin = node < min ? node : min;
            minStack.push(newMin);
        }

    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }

}
