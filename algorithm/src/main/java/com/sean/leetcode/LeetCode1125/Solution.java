package com.sean.leetcode.LeetCode1125;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-10 08:24
 * @Description: https://leetcode.cn/problems/smallest-sufficient-team/
 * 1125. 最小的必要团队
 * 作为项目经理，你规划了一份需求的技能清单 req_skills，
 * 并打算从备选人员名单 people 中选出些人组成一个「必要团队」
 * （ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
 * 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，
 * 团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：
 * 例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
 * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按 任意顺序 返回答案，题目数据保证答案存在。
 */
public class Solution {

    public int[] smallestSufficientTeam1(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int m = people.size();
        Map<String, Integer> skill_index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skill_index.put(req_skills[i], i);
        }
        List<Integer>[] dp = new List[1 << n];
        dp[0] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int cur_skill = 0;
            for (String s : people.get(i)) {
                cur_skill |= 1 << skill_index.get(s);
            }
            for (int prev = 0; prev < dp.length; prev++) {
                if (dp[prev] == null) {
                    continue;
                }
                int comb = prev | cur_skill;
                if (dp[comb] == null || dp[prev].size() + 1 < dp[comb].size()) {
                    dp[comb] = new ArrayList<>(dp[prev]);
                    dp[comb].add(i);
                }
            }
        }
        //return dp[(1 << n) - 1].stream().mapToInt(i -> i).toArray();
        int size = dp[(1 << n) - 1].size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = dp[(1 << n) - 1].get(i);
        }
        return res;
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int m = people.size();
        Map<String, Integer> skill_index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skill_index.put(req_skills[i], i);
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, m);
        dp[0] = 0;
        int[] prev_skill = new int[1 << n];
        int[] prev_people = new int[1 << n];
        for (int i = 0; i < m; i++) {
            List<String> p = people.get(i);
            int cur_skill = 0;
            for (String s : p) {
                cur_skill |= 1 << skill_index.get(s);
            }
            for (int prev = 0; prev < 1 << n; prev++) {
                int comb = prev | cur_skill;
                if (dp[comb] > dp[prev] + 1) {
                    dp[comb] = dp[prev] + 1;
                    prev_skill[comb] = prev;
                    prev_people[comb] = i;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int i = (1 << n) - 1;
        while (i > 0) {
            res.add(prev_people[i]);
            i = prev_skill[i];
        }
        return res.stream().mapToInt(j -> j).toArray();
    }

}
