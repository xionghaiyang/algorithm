package com.sean.leetcode.LeetCode1172;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-04 08:05
 * @Description: https://leetcode.cn/problems/dinner-plate-stacks/
 * 1172. 餐盘栈
 * 我们把无限数量 ∞ 的栈排成一行，按从左到右的次序从 0 开始编号。
 * 每个栈的的最大容量 capacity 都相同。
 * 实现一个叫「餐盘」的类 DinnerPlates：
 * DinnerPlates(int capacity) - 给出栈的最大容量 capacity。
 * void push(int val) - 将给出的正整数 val 推入 从左往右第一个 没有满的栈。
 * int pop() - 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1。
 * int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -1。
 */
public class DinnerPlates {

    int capacity;
    List<Integer> stack;
    List<Integer> top;
    TreeSet<Integer> poppedPos;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        stack = new LinkedList<>();
        top = new ArrayList<>();
        poppedPos = new TreeSet<>();
    }

    //将给出的正整数 val 推入 从左往右第一个 没有满的栈。
    public void push(int val) {
        if (poppedPos.isEmpty()) {
            int pos = stack.size();
            stack.add(val);
            if (pos % capacity == 0) {
                top.add(0);
            } else {
                int index = top.size() - 1;
                top.set(index, top.get(index) + 1);
            }
        } else {
            int pos = poppedPos.pollFirst();
            stack.set(pos, val);
            int index = pos / capacity;
            top.set(index, top.get(index) + 1);
        }
    }

    //返回从右往左第一个非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1
    public int pop() {
        while (!stack.isEmpty() && poppedPos.contains(stack.size() - 1)) {
            stack.remove(stack.size() - 1);
            int pos = poppedPos.pollLast();
            if (pos % capacity == 0) {
                top.remove(top.size() - 1);
            }
        }
        if (stack.isEmpty()) {
            return -1;
        } else {
            int pos = stack.size() - 1;
            int val = stack.get(pos);
            stack.remove(pos);
            int index = top.size() - 1;
            if (pos % capacity == 0) {
                top.remove(index);
            } else {
                top.set(index, top.get(index) - 1);
            }
            return val;
        }
    }

    //返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -1
    public int popAtStack(int index) {
        if (index >= top.size()) {
            return -1;
        }
        int stackTop = top.get(index);
        if (stackTop < 0) {
            return -1;
        }
        top.set(index, stackTop - 1);
        int pos = index * capacity + stackTop;
        poppedPos.add(pos);
        return stack.get(pos);
    }

}
