package com.sean.leetcode.LeetCodeInterview0301;

/**
 * @Author xionghaiyang
 * @Date 2025-08-01 12:25
 * @Description https://leetcode.cn/problems/three-in-one-lcci
 * 面试题 03.01. 三合一
 * 三合一。
 * 描述如何只用一个数组来实现三个栈。
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。
 * stackNum表示栈下标，value表示压入的值。
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 */
public class TripleInOne {

    private Integer[] values;
    private Integer[] indexes;

    public TripleInOne(int stackSize) {
        values = new Integer[stackSize * 3];
        indexes = new Integer[3];
    }

    public void push(int stackNum, int value) {
        int index = indexes[stackNum] != null ? indexes[stackNum] + 3 : stackNum;
        if (index < values.length) {
            values[index] = value;
            indexes[stackNum] = index;
        }
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        int index = indexes[stackNum];
        int value = values[index];
        values[index] = value;
        indexes[stackNum] = index < 3 ? null : index - 3;
        return value;
    }

    public int peek(int stackNum) {
        return isEmpty(stackNum) ? -1 : values[indexes[stackNum]];
    }

    public boolean isEmpty(int stackNum) {
        return indexes[stackNum] == null;
    }

}
