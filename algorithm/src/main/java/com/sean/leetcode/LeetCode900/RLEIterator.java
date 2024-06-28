package com.sean.leetcode.LeetCode900;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 13:19
 * @Description: https://leetcode.cn/problems/rle-iterator/description/
 * 900. RLE 迭代器
 * 我们可以使用游程编码(即 RLE )来编码一个整数序列。
 * 在偶数长度 encoding ( 从 0 开始 )的游程编码数组中，对于所有偶数 i ，encoding[i] 告诉我们非负整数 encoding[i + 1] 在序列中重复的次数。
 * 例如，序列 arr = [8,8,8,5,5] 可以被编码为 encoding =[3,8,2,5] 。encoding =[3,8,0,9,2,5] 和 encoding =[2,8,1,8,2,5] 也是 arr 有效的 RLE 。
 * 给定一个游程长度的编码数组，设计一个迭代器来遍历它。
 * 实现 RLEIterator 类:
 * RLEIterator(int[] encoded) 用编码后的数组初始化对象。
 * int next(int n) 以这种方式耗尽后 n 个元素并返回最后一个耗尽的元素。如果没有剩余的元素要耗尽，则返回 -1 。
 */
public class RLEIterator {

    private int[] encoded;
    private int i;
    private int cur;

    public RLEIterator(int[] encoded) {
        this.encoded = encoded;
        i = 0;
        cur = 0;
    }

    public int next(int n) {
        while (i < encoded.length) {
            if (cur + n > encoded[i]) {
                n -= encoded[i] - cur;
                cur = 0;
                i += 2;
            } else {
                cur += n;
                return encoded[i + 1];
            }
        }
        return -1;
    }

}
