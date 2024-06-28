package com.sean.nowcoder;

import java.util.Stack;

public class NowCoderBM42 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        if (stack1.isEmpty()) {
            throw new RuntimeException("queue is empty");
        }
        int size = stack1.size();
        for (int i = 1; i < size; i++) {
            stack2.push(stack1.pop());
        }
        return stack1.pop();
    }

}
