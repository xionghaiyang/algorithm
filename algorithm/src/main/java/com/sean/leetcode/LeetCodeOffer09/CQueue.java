package com.sean.leetcode.LeetCodeOffer09;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 21:38
 * @Description: https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
public class CQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public CQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
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
