package com.sean.leetcode.LeetCodeInterview0305;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-10-01 19:37
 * @Description https://leetcode.cn/problems/sort-of-stacks-lcci
 * 面试题 03.05. 栈排序
 * 栈排序。
 * 编写程序，对栈进行排序使最小元素位于栈顶。
 * 最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。
 * 当栈为空时，peek 返回 -1。
 * 栈中的元素数目在[0, 5000]范围内。
 */
public class SortedStack {

    private Deque<Integer> stack;
    private Deque<Integer> helpStack;

    public SortedStack() {
        stack = new ArrayDeque<>();
        helpStack = new ArrayDeque<>();
    }

    public void push(int val) {
        while (!stack.isEmpty() && val > stack.peek()) {
            helpStack.push(stack.pop());
        }
        stack.push(val);
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public int peek() {
        return stack.isEmpty() ? -1 : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
