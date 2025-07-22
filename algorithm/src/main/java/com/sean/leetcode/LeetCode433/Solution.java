package com.sean.leetcode.LeetCode433;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-07-22 09:46
 * @Description https://leetcode.cn/problems/minimum-genetic-mutation
 * 433. 最小基因变化
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。
 * 一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * （变化后的基因必须位于基因库 bank 中）
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。
 * 如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * start.length == 8
 * end.length == 8
 * 0 <= bank.length <= 10
 * bank[i].length == 8
 * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
 */
public class Solution {

    public int minMutation(String startGene, String endGene, String[] bank) {
        Map<String, Integer> map = new HashMap<>();
        for (String gene : bank) {
            map.put(gene, -1);
        }
        if (!map.containsKey(endGene)) {
            return -1;
        }
        char[] basic = {'A', 'C', 'G', 'T'};
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(startGene, 0));
        while (!queue.isEmpty()) {
            Pair<String, Integer> cur = queue.poll();
            char[] gene = cur.getKey().toCharArray();
            int cnt = cur.getValue();
            for (int i = 0; i < 8; i++) {
                char c = gene[i];
                for (char type : basic) {
                    if (c == type) {
                        continue;
                    }
                    gene[i] = type;
                    String next = String.valueOf(gene);
                    if (next.equals(endGene)) {
                        return cnt + 1;
                    }
                    if (map.containsKey(next) && map.get(next) == -1) {
                        queue.offer(new Pair<>(next, cnt + 1));
                        map.put(next, cnt + 1);
                    }
                }
                gene[i] = c;
            }
        }
        return -1;
    }

}
