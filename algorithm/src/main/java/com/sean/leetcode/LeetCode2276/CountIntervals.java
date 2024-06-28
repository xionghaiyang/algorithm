package com.sean.leetcode.LeetCode2276;

import java.util.TreeMap;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-16 17:14
 * @Description: https://leetcode.cn/problems/count-integers-in-intervals/
 * 2276. 统计区间中的整数数目
 * 给你区间的 空 集，请你设计并实现满足要求的数据结构：
 * 新增：添加一个区间到这个区间集合中。
 * 统计：计算出现在 至少一个 区间中的整数个数。
 * 实现 CountIntervals 类：
 * CountIntervals() 使用区间的空集初始化对象
 * void add(int left, int right) 添加区间 [left, right] 到区间集合之中。
 * int count() 返回出现在 至少一个 区间中的整数个数。
 * 注意：区间 [left, right] 表示满足 left <= x <= right 的所有整数 x 。
 */
public class CountIntervals {

    private TreeMap<Integer, Integer> map;
    private int cnt;

    public CountIntervals() {
        map = new TreeMap<>();
        cnt = 0;
    }

    public void add(int left, int right) {
        //返回小于等于right的最大的那个key
        Integer key = map.floorKey(right);
        //当它们区间有重叠时
        while (key != null && left <= map.get(key)) {
            left = Math.min(left, key);
            right = Math.max(right, map.get(key));
            //合并区间之后，要减去原来加上去的值
            cnt -= map.get(key) - key + 1;
            //移除原区间
            map.remove(key);
            key = map.floorKey(right);
        }
        cnt += right - left + 1;
        map.put(left, right);
    }

    public int count() {
        return cnt;
    }

}
