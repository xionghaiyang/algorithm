package com.sean.leetcode.LeetCodeInterview0806;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 19:39
 * @Description https://leetcode.cn/problems/hanota-lcci
 * 面试题 08.06. 汉诺塔问题
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
 * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。
 * 移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * 你需要原地修改栈。
 */
public class Solution {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        movePlant(A.size(), A, B, C);
    }

    private void movePlant(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        }
        //将n-1个盘子从第一个柱子移动到第二个柱子
        movePlant(n - 1, A, C, B);
        //然后将最后一个盘子移动到第三个柱子上
        C.add(A.remove(A.size() - 1));
        //最后将第二个柱子上的n-1个盘子，移动到第三个柱子上
        movePlant(n - 1, B, A, C);
    }

}
