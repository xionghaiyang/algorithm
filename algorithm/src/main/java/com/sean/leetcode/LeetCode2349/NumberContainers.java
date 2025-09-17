package com.sean.leetcode.LeetCode2349;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-17 09:22
 * @Description https://leetcode.cn/problems/design-a-number-container-system
 * 2349. 设计数字容器系统
 * 设计一个数字容器系统，可以实现以下功能：
 * 在系统中给定下标处 插入 或者 替换 一个数字。
 * 返回 系统中给定数字的最小下标。
 * 请你实现一个 NumberContainers 类：
 * NumberContainers() 初始化数字容器系统。
 * void change(int index, int number) 在下标 index 处填入 number 。
 * 如果该下标 index 处已经有数字了，那么用 number 替换该数字。
 * int find(int number) 返回给定数字 number 在系统中的最小下标。
 * 如果系统中没有 number ，那么返回 -1 。
 * 1 <= index, number <= 10^9
 * 调用 change 和 find 的 总次数 不超过 10^5 次。
 */
public class NumberContainers {

    private Map<Integer, Integer> index2Number;
    private Map<Integer, PriorityQueue<Integer>> number2Heap;

    public NumberContainers() {
        index2Number = new HashMap<>();
        number2Heap = new HashMap<>();
    }

    public void change(int index, int number) {
        index2Number.put(index, number);
        number2Heap.computeIfAbsent(number, e -> new PriorityQueue<>()).offer(index);
    }

    public int find(int number) {
        PriorityQueue<Integer> heap = number2Heap.get(number);
        if (heap == null) {
            return -1;
        }
        while (!heap.isEmpty() && index2Number.get(heap.peek()) != number) {
            heap.poll();
        }
        return heap.isEmpty() ? -1 : heap.peek();
    }

}
