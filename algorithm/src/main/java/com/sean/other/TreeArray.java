package com.sean.other;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-25 08:52
 * @Description: 假设有一个数组，对它大量的修改和查询，
 * 修改的是数组中某一个元素的值，查询的是数组中任意一个区间的和
 */
public class TreeArray {

    private int[] a;//原始数组，有效下标从0开始
    private int[] c;//树状数组，有效下标从1开始

    public TreeArray(int[] a) {
        this.a = a;
        this.c = new int[a.length + 1];//创建树状数组
        for (int i = 0; i < a.length; i++) {
            add(i + 1, a[i]);
        }
    }

    //把数组c中i位置的元素加上val
    private void add(int i, int val) {
        while (i < c.length) {
            c[i] += val;
            i += lowBit(i);//继续找父节点
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    //修改树状数组的值
    public void update(int i, int val) {
        add(i + 1, val - a[i]);
        a[i] = val;
    }

    //求数组区间[left,right]的值
    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private int prefixSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += c[i];
            i -= lowBit(i);
        }
        return sum;
    }

}
