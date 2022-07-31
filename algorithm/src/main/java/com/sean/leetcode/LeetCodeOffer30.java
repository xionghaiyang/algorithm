package com.sean.leetcode;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/1/29 16:47
 * @Description: https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 * 剑指 Offer 30. 包含min函数的栈
 * @version: 1.0
 */
public class LeetCodeOffer30 {

    class MinStack {

        private Stack<Integer> A = null;
        private Stack<Integer> B = null;

        public MinStack() {
            A = new Stack<Integer>();
            B = new Stack<Integer>();
        }

        public void push(int x) {
            A.push(x);
            if (B.isEmpty() || B.peek() >= x) {
                B.push(x);
            }
        }

        public void pop() {
            if (B.peek().equals(A.pop())) {
                B.pop();
            }
        }

        public int top() {
            return A.peek();
        }

        public int min() {
            return B.peek();
        }
    }

}
