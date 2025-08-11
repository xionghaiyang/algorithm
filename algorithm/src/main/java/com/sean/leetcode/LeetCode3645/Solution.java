package com.sean.leetcode.LeetCode3645;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-11 20:07
 * @Description https://leetcode.cn/problems/maximum-total-from-optimal-activation-order
 * 3645. 最优激活顺序得到的最大总和
 * 给你两个长度为 n 的整数数组 value 和 limit。
 * 初始时，所有元素都是 非活跃 的。你可以按任意顺序激活它们。
 * 要激活一个非活跃元素 i，当前 活跃元素的数量必须 严格小于 limit[i]。
 * 当你激活元素 i 时，它的 value[i] 会被加到 总和 中（即所有进行过激活操作的元素 value[i] 之和）。
 * 每次激活后，如果 当前 活跃元素的数量变为 x，那么 所有 满足 limit[j] <= x 的元素 j 都会永久变为非活跃状态，即使它们已经处于活跃状态。
 * 返回通过最优选择激活顺序可以获得的 最大总和 。
 * 1 <= n == value.length == limit.length <= 10^5
 * 1 <= value[i] <= 10^5
 * 1 <= limit[i] <= n
 */
public class Solution {

    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        List<Integer>[] groups = new ArrayList[n + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            groups[limit[i]].add(value[i]);
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            List<Integer> list = groups[i];
            list.sort(Collections.reverseOrder());
            if (list.size() > i) {
                list = list.subList(0, i);
            }
            for (int val : list) {
                res += val;
            }
        }
        return res;
    }

}
