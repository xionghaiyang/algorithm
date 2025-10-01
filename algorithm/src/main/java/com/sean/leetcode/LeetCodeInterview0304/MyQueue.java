package com.sean.leetcode.LeetCodeInterview0304;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-10-01 18:50
 * @Description https://leetcode.cn/problems/implement-queue-using-stacks-lcci
 * 面试题 03.04. 化栈为队
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。
 * 你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 */
public class MyQueue {

    private Deque<Integer> inStack;
    private Deque<Integer> outStack;

    public MyQueue() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if(outStack.isEmpty()){
            in2out();
        }
        if(!outStack.isEmpty()){
            return outStack.pop();
        }
        return -1;
    }

    private void in2out(){
        while (!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }

    public int peek() {
        if(outStack.isEmpty()){
            in2out();
        }
        if(!outStack.isEmpty()){
            return outStack.peek();
        }
        return -1;
    }

    public boolean empty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }

}
