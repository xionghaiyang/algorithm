package com.sean.leetcode.LeetCode838;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-05-02 08:06
 * @Description https://leetcode.cn/problems/push-dominoes
 * 838. 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。
 * 在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。
 * 同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * n == dominoes.length
 * 1 <= n <= 10^5
 * dominoes[i] 为 'L'、'R' 或 '.'
 */
public class Solution {

    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] str = dominoes.toCharArray();
        int[] steps = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (str[i] != '.') {
                queue.offer(i);
                steps[i] = 1;
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                if (str[index] == 'L') {
                    if (index - 1 >= 0 && steps[index - 1] == 0 && str[index - 1] == '.') {
                        if (index - 2 >= 0 && steps[index - 2] == step && str[index - 2] == 'R') {
                            steps[index - 1] = step;
                        } else {
                            str[index - 1] = 'L';
                            queue.offer(index - 1);
                            steps[index - 1] = step + 1;
                        }
                    }
                } else {//str[index] == 'R'
                    if (index + 1 < n && steps[index + 1] == 0 && str[index + 1] == '.') {
                        if (index + 2 < n && steps[index + 2] == step && str[index + 2] == 'L') {
                            steps[index + 1] = step;
                        } else {
                            str[index + 1] = 'R';
                            queue.offer(index + 1);
                            steps[index + 1] = step + 1;
                        }
                    }
                }
            }
        }
        return new String(str);
    }

}
