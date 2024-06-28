package com.sean.leetcode.LeetCode1237;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-20 08:25
 * @Description: https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation/
 * 1237. 找出给定方程的正整数解
 * 给你一个函数  f(x, y) 和一个目标结果 z，函数公式未知，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。满足条件的结果数对可以按任意顺序返回。
 * 尽管函数的具体式子未知，但它是单调递增函数，也就是说：
 * f(x, y) < f(x + 1, y)
 * f(x, y) < f(x, y + 1)
 * 函数接口定义如下：
 * interface CustomFunction {
 * public:
 * // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
 * int f(int x, int y);
 * };
 * 你的解决方案将按如下规则进行评判：
 * 判题程序有一个由 CustomFunction 的 9 种实现组成的列表，以及一种为特定的 z 生成所有有效数对的答案的方法。
 * 判题程序接受两个输入：function_id（决定使用哪种实现测试你的代码）以及目标结果 z 。
 * 判题程序将会调用你实现的 findSolution 并将你的结果与答案进行比较。
 * 如果你的结果与答案相符，那么解决方案将被视作正确答案，即 Accepted 。
 * 1 <= x, y <= 1000
 */
public class Solution {

    interface CustomFunction {
        int f(int x, int y);
    }

    public List<List<Integer>> findSolution1(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x <= 1000; x++) {
            for (int y = 1; y <= 1000; y++) {
                if (customfunction.f(x, y) == z) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(x);
                    pair.add(y);
                    res.add(pair);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> findSolution2(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int x = 1; x <= 1000; x++) {
            int yleft = 1, yright = 1000;
            while (yleft <= yright) {
                int ymid = (yleft + yright) / 2;
                if (customfunction.f(x, ymid) == z) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(x);
                    pair.add(ymid);
                    res.add(pair);
                    break;
                }
                if (customfunction.f(x, ymid) > z) {
                    yright = ymid - 1;
                } else {
                    yleft = ymid + 1;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int x = 1, y = 1000; x <= 1000 && y >= 1; x++) {
            while (y >= 1 && customfunction.f(x, y) > z) {
                y--;
            }
            if (y >= 1 && customfunction.f(x, y) == z) {
                List<Integer> pair = new ArrayList<>();
                pair.add(x);
                pair.add(y);
                res.add(pair);
            }
        }
        return res;
    }

}
