package com.sean.lintcode.LintCode1665;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2022-09-11 11:21
 * @Description https://www.lintcode.com/problem/1665/?showListFe=true&page=1&pageSize=50
 * 1665 · 计算数字
 * 给出一个十进制数num，现在你需要把它转成二进制数，并返回1的个数和位置。
 */
public class Solution {

    public int[] calculateNumber(int num) {
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        int index = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                cnt++;
                list.add(index);
            }
            num >>= 1;
            index++;
        }
        int[] res = new int[cnt + 1];
        res[0] = cnt;
        for (int i = 1; i <= cnt; i++) {
            res[i] = index - list.get(list.size() - i);
        }
        return res;
    }

}
