package com.sean.leetcode.LeetCode2671;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-21 20:13
 * @Description: https://leetcode.cn/problems/frequency-tracker/
 * 2671. 频率跟踪器
 * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
 * 实现 FrequencyTracker 类：
 * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
 * void add(int number)：添加一个 number 到数据结构中。
 * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number ，在这种情况下不删除任何内容。
 * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
 */
public class FrequencyTracker {

    private static final int N = 100001;
    private int[] freq;//每个number出现的频率
    private int[] freqCnt;//各个频率出现的次数

    public FrequencyTracker() {
        freq = new int[N];
        freqCnt = new int[N];
    }

    public void add(int number) {
        freqCnt[freq[number]]--;
        freq[number]++;
        freqCnt[freq[number]]++;
    }

    public void deleteOne(int number) {
        if (freq[number] == 0) {
            return;
        }
        freqCnt[freq[number]]--;
        freq[number]--;
        freqCnt[freq[number]]++;
    }

    public boolean hasFrequency(int frequency) {
        return freqCnt[frequency] > 0;
    }

}
