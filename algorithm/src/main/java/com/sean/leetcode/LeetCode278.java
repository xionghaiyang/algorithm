package com.sean.leetcode;

/**
 * 第一个错误的版本
 * https://leetcode-cn.com/problems/first-bad-version/
 */
public class LeetCode278 {

    //测试方法
    private boolean isBadVersion(int version) {
        return true;
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n;
        //循环直至区间左右端点相同
        while (left < right) {
            //防止计算时溢出
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                //答案在区间[left,mid]中
                right = mid;
            } else {
                //答案在区间[mid + 1,right]中
                left = mid + 1;
            }
        }
        //此时有left == right,区间缩为一个点，即为答案
        return left;
    }
}
