package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: xionghaiyang
 * @Date: 2022/3/31 14:14
 * @Description: https://leetcode-cn.com/problems/self-dividing-numbers/
 * 自除数
 * @version: 1.0
 */
public class LeetCode728 {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumber(i)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean isSelfDividingNumber(int num) {
        int temp = num;
        while (temp > 0) {
            int digit = temp % 10;
            if (digit == 0 || num % digit != 0) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

}
