package com.sean.lintcode.LintCode2878;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-19 11:50
 * @Description: https://www.lintcode.com/problem/2878/?showListFe=true&page=1&problemTypeId=11&pageSize=50
 * 2878 · 3 和 3 的倍数
 * 描述
 * 朋友或同学聚会中，难免会玩一些小游戏。 而 不能说 3 和 3 的倍数 就是一个比较常见的游戏，
 * 游戏规则：参与人员随机选一人开始报数，然后顺时针的下一位继续报数（上一个数字 +1）。
 * 如果在报数的过程中遇到 3 或 3 的倍数，需要拍手跳过，
 * 如果说的这个数字包含 3 或者是 3 的倍数，那么游戏结束，该玩家接受惩罚。
 */
public class Solution {

    public List<Integer> removeNumber(List<Integer> list) {
        List<Integer> res = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if (num % 3 == 0) {
                continue;
            }
            flag = false;
            while (num != 0) {
                if (num % 10 == 3) {
                    flag = true;
                    break;
                }
                num /= 10;
            }
            if (flag) {
                continue;
            }
            res.add(list.get(i));
        }
        return res;
    }

}
