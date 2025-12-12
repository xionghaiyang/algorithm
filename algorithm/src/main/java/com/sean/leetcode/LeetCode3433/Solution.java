package com.sean.leetcode.LeetCode3433;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-12-12 12:23
 * @Description https://leetcode.cn/problems/count-mentions-per-user
 * 3433. 统计用户被提及情况
 * 给你一个整数 numberOfUsers 表示用户总数，另有一个大小为 n x 3 的数组 events 。
 * 每个 events[i] 都属于下述两种类型之一：
 * 消息事件（Message Event）：["MESSAGE", "timestampi", "mentions_stringi"]
 * 事件表示在 timestampi 时，一组用户被消息提及。
 * mentions_stringi 字符串包含下述标识符之一：
 * id<number>：其中 <number> 是一个区间 [0,numberOfUsers - 1] 内的整数。
 * 可以用单个空格分隔 多个 id ，并且 id 可能重复。
 * 此外，这种形式可以提及离线用户。
 * ALL：提及 所有 用户。
 * HERE：提及所有 在线 用户。
 * 离线事件（Offline Event）：["OFFLINE", "timestampi", "idi"]
 * 事件表示用户 idi 在 timestampi 时变为离线状态 60 个单位时间。
 * 用户会在 timestampi + 60 时自动再次上线。
 * 返回数组 mentions ，其中 mentions[i] 表示  id 为  i 的用户在所有 MESSAGE 事件中被提及的次数。
 * 最初所有用户都处于在线状态，并且如果某个用户离线或者重新上线，其对应的状态变更将会在所有相同时间发生的消息事件之前进行处理和同步。
 * 注意 在单条消息中，同一个用户可能会被提及多次。
 * 每次提及都需要被 分别 统计。
 * 1 <= numberOfUsers <= 100
 * 1 <= events.length <= 100
 * events[i].length == 3
 * events[i][0] 的值为 MESSAGE 或 OFFLINE 。
 * 1 <= int(events[i][1]) <= 10^5
 * 在任意 "MESSAGE" 事件中，以 id<number> 形式提及的用户数目介于 1 和 100 之间。
 * 0 <= <number> <= numberOfUsers - 1
 * 题目保证 OFFLINE 引用的用户 id 在事件发生时处于 在线 状态。
 */
public class Solution {

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        Collections.sort(events, (a, b) -> {
            int t1 = Integer.parseInt(a.get(1));
            int t2 = Integer.parseInt(b.get(1));
            return t1 != t2 ? t1 - t2 : b.get(0).charAt(0) - a.get(0).charAt(0);
        });
        int[] res = new int[numberOfUsers];
        int[] onLineTime = new int[numberOfUsers];
        for (List<String> event : events) {
            int curTime = Integer.parseInt(event.get(1));
            String mention = event.get(2);
            if (event.get(0).charAt(0) == 'O') {//离线
                onLineTime[Integer.parseInt(mention)] = curTime + 60;
            } else if (mention.charAt(0) == 'A') {
                for (int i = 0; i < numberOfUsers; i++) {
                    res[i]++;
                }
            } else if (mention.charAt(0) == 'H') {
                for (int i = 0; i < numberOfUsers; i++) {
                    if (onLineTime[i] <= curTime) {
                        res[i]++;
                    }
                }
            } else {
                for (String s : mention.split(" ")) {
                    int i = Integer.parseInt(s.substring(2));
                    res[i]++;
                }
            }
        }
        return res;
    }

    public int[] countMentions1(int numberOfUsers, List<List<String>> events) {
        int[] res = new int[numberOfUsers];
        //(timestamp,type,id)
        //type  -1:离线,1:在线,2:HERE
        List<int[]> es = new ArrayList<>();
        int all = 0;
        for (List<String> event : events) {
            int curTime = Integer.parseInt(event.get(1));
            String mention = event.get(2);
            if (event.get(0).charAt(0) == 'O') {//离线事件
                int i = Integer.parseInt(mention);
                es.add(new int[]{curTime, 1, i});//离线
                es.add(new int[]{curTime + 60, -1, i});//在线
            } else if (mention.charAt(0) == 'A') {//所有用户
                all++;
            } else if (mention.charAt(0) == 'H') {//HERE
                all++;
                es.add(new int[]{curTime, 2, -1});
            } else {
                for (String s : mention.split(" ")) {
                    int i = Integer.parseInt(s.substring(2));
                    res[i]++;
                }
            }
        }
        es.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int here = 0;
        for (int[] e : es) {
            int type = e[1];
            if (type == 2) {
                here++;
            } else {
                res[e[2]] += type * here;
            }
        }
        for (int i = 0; i < numberOfUsers; i++) {
            res[i] += all;
        }
        return res;
    }

}
