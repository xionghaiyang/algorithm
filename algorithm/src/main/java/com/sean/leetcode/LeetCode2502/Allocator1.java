package com.sean.leetcode.LeetCode2502;

/**
 * @Author xionghaiyang
 * @Date 2025-02-25 20:36
 * @Description TODO
 */
public class Allocator1 {

    private int n;
    private int[] memory;
    private int[] len;

    public Allocator1(int n) {
        this.n = n;
        memory = new int[n];
        len = new int[n];
        len[0] = n;
    }

    public int allocate(int size, int mID) {
        for (int i = 0; i < n; i += len[i]) {
            if (memory[i] == 0 && len[i] >= size) {
                if (i + size < n && memory[i + size] == 0) {
                    len[i + size] = len[i] - size;
                }
                memory[i] = mID;
                len[i] = size;
                return i;
            }
        }
        return -1;
    }

    public int freeMemory(int mID) {
        int res = 0;
        for (int i = 0; i < n; i += len[i]) {
            if (memory[i] == mID) {
                res += len[i];
                memory[i] = 0;
            }
        }
        for (int i = 0; i < n; i += len[i]) {
            if (memory[i] == 0) {
                int j = i + len[i];
                while (j < n && memory[j] == 0) {
                    j += len[j];
                }
                len[i] = j - i;
            }
        }
        return res;
    }

}
