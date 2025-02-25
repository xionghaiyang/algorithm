package com.sean.leetcode.LeetCode2502;

/**
 * @Author xionghaiyang
 * @Date 2025-02-25 20:22
 * @Description https://leetcode.cn/problems/design-memory-allocator/
 * 2502. 设计内存分配器
 * 给你一个整数 n ，表示下标从 0 开始的内存数组的大小。
 * 所有内存单元开始都是空闲的。
 * 请你设计一个具备以下功能的内存分配器：
 * 分配 一块大小为 size 的连续空闲内存单元并赋 id mID 。
 * 释放 给定 id mID 对应的所有内存单元。
 * 注意：
 * 多个块可以被分配到同一个 mID 。
 * 你必须释放 mID 对应的所有内存单元，即便这些内存单元被分配在不同的块中。
 * 实现 Allocator 类：
 * Allocator(int n) 使用一个大小为 n 的内存数组初始化 Allocator 对象。
 * int allocate(int size, int mID) 找出大小为 size 个连续空闲内存单元且位于  最左侧 的块，分配并赋 id mID 。
 * 返回块的第一个下标。如果不存在这样的块，返回 -1 。
 * int freeMemory(int mID) 释放 id mID 对应的所有内存单元。返回释放的内存单元数目。
 * 1 <= n, size, mID <= 1000
 * 最多调用 allocate 和 free 方法 1000 次
 */
public class Allocator {

    private int n;
    private int[] memory;

    public Allocator(int n) {
        this.n = n;
        memory = new int[n];
    }

    public int allocate(int size, int mID) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (memory[i] != 0) {
                count = 0;
            } else {
                count++;
                if (count == size) {
                    for (int j = i - count + 1; j <= i; j++) {
                        memory[j] = mID;
                    }
                    return i - count + 1;
                }
            }
        }
        return -1;
    }

    public int freeMemory(int mID) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (memory[i] == mID) {
                res++;
                memory[i] = 0;
            }
        }
        return res;
    }

}
