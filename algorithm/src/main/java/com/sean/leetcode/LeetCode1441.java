package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 用栈操作构建数组
 * https://leetcode-cn.com/problems/build-an-array-with-stack-operations/
 */
public class LeetCode1441 {

    public List<String> buildArray(int[] target, int n) {
        ArrayList<String> resList = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < target.length; i++) {
            for (int j = t + 1; j <= n; j++) {
                if (target[i] > j) {
                    resList.add("Push");
                    resList.add("Pop");
                } else if (target[i] == j) {
                    resList.add("Push");
                    t = j;
                    break;
                }
            }
        }
        return resList;
    }

}
