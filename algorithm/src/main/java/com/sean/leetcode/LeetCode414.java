package com.sean.leetcode;

import java.util.TreeSet;

/**
 * https://leetcode-cn.com/problems/third-maximum-number/
 * 第三大的数
 */
public class LeetCode414 {

    public int thirdMax(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
            if (treeSet.size() > 3) {
                treeSet.remove(treeSet.first());
            }
        }
        return treeSet.size() == 3 ? treeSet.first() : treeSet.last();
    }

}
