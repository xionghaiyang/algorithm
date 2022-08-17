package com.sean.lintcode.LintCode334;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-17 11:55
 * @Description: https://www.lintcode.com/problem/334/?showListFe=true&page=1&pageSize=50
 * 334 · 队列检查
 * 班上的学生根据他们的年级照片的身高升序排列，确定当前未站在正确位置的学生人数
 */
public class Solution {

    public int orderCheck(List<Integer> heights) {
        List<Integer> sortedList = new ArrayList<>(heights);
        Collections.sort(sortedList);
        int res = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (heights.get(i) != sortedList.get(i)) {
                res++;
            }
        }
        return res;
    }

}
