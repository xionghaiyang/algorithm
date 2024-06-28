package com.sean.leetcode.LeetCode1073;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-18 08:06
 * @Description: https://leetcode.cn/problems/adding-two-negabinary-numbers/
 * 1073. 负二进制数相加
 * 给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。
 * 数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。
 * 例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。
 * 数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。
 * 返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
 */
public class Solution {

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int carry = 0;
        List<Integer> list = new ArrayList<>();
        while (i >= 0 || j >= 0 || carry != 0) {
            int t = carry;
            if (i >= 0) {
                t += arr1[i];
            }
            if (j >= 0) {
                t += arr2[j];
            }
            if (t >= 2) {
                list.add(t - 2);
                carry = -1;
            } else if (t >= 0) {
                list.add(t);
                carry = 0;
            } else {
                list.add(1);
                carry = 1;
            }
            i--;
            j--;
        }
        while (list.size() > 1 && list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }
        int[] res = new int[list.size()];
        for (i = 0, j = list.size() - 1; j >= 0; i++, j--) {
            res[i] = list.get(j);
        }
        return res;
    }

}
