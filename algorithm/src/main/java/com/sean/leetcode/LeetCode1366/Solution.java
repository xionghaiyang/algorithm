package com.sean.leetcode.LeetCode1366;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 16:24
 * @Description: https://leetcode.cn/problems/rank-teams-by-votes/description/
 * 1366. 通过投票对团队排名
 * 现在有一个特殊的排名系统，依据参赛团队在投票人心中的次序进行排名，每个投票者都需要按从高到低的顺序对参与排名的所有团队进行排位。
 * 排名规则如下：
 * 参赛团队的排名次序依照其所获「排位第一」的票的多少决定。
 * 如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。
 * 以此类推，直到不再存在并列的情况。
 * 如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。
 * 给你一个字符串数组 votes 代表全体投票者给出的排位情况，请你根据上述排名规则对所有参赛团队进行排名。
 * 请你返回能表示按排名系统 排序后 的所有团队排名的字符串。
 */
public class Solution {

    public String rankTeams(String[] votes) {
        Map<Character, List<Integer>> map = new HashMap<>();
        int n = votes[0].length();
        for (char c : votes[0].toCharArray()) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(0);
            }
            map.put(c, list);
        }
        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                map.get(vote.charAt(i)).set(i, map.get(vote.charAt(i)).get(i) + 1);
            }
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                List<Integer> list1 = map.get(o1);
                List<Integer> list2 = map.get(o2);
                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i) > list2.get(i)) {
                        return -1;
                    } else if (list1.get(i) == list2.get(i)) {
                        continue;
                    } else {
                        return 1;
                    }
                }
                return o1.compareTo(o2);
            }
        });
        StringBuilder res = new StringBuilder();
        for (Character character : list) {
            res.append(character);
        }
        return res.toString();
    }

}
