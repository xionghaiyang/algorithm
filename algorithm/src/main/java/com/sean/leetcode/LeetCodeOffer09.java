package com.sean.leetcode;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/1/29 16:32
 * @Description: https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 剑指 Offer 09. 用两个栈实现队列
 * @version: 1.0
 */
public class LeetCodeOffer09 {
    class CQueue {

        private Stack<Integer> inStack = null;
        private Stack<Integer> outStack = null;

        public CQueue() {
            inStack = new Stack<Integer>();
            outStack = new Stack<Integer>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (!outStack.isEmpty()) {
                return outStack.pop();
            } else {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
                return outStack.isEmpty() ? -1 : outStack.pop();
            }
        }
    }
}
