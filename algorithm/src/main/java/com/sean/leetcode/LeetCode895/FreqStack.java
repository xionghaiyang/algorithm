package com.sean.leetcode.LeetCode895;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-30 08:22
 * @Description: https://leetcode.cn/problems/maximum-frequency-stack/
 * 895. 最大频率栈
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 * 实现 FreqStack 类:
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 */
public class FreqStack {

    private HashMap<Integer, Integer> freq;
    private HashMap<Integer, Stack<Integer>> group;
    private int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        freq.put(val, freq.getOrDefault(val, 0) + 1);
        group.putIfAbsent(freq.get(val), new Stack<>());
        group.get(freq.get(val)).push(val);
        maxFreq = Math.max(maxFreq, freq.get(val));
    }

    public int pop() {
        int val = group.get(maxFreq).peek();
        freq.put(val, freq.get(val) - 1);
        group.get(maxFreq).pop();
        if (group.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        return val;
    }

}
