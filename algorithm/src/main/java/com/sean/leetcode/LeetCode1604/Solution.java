package com.sean.leetcode.LeetCode1604;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-07 09:29
 * @Description: https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 * 1604. 警告一小时内使用相同员工卡大于等于三次的人
 * 力扣公司的员工都使用员工卡来开办公室的门。
 * 每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。
 * 如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个 警告 。
 * 给你字符串数组 keyName 和 keyTime ，其中 [keyName[i], keyTime[i]] 对应一个人的名字和他在 某一天 内使用员工卡的时间。
 * 使用时间的格式是 24小时制 ，形如 "HH:MM" ，比方说 "23:51" 和 "09:49" 。
 * 请你返回去重后的收到系统警告的员工名字，将它们按 字典序升序 排序后返回。
 * 请注意 "10:00" - "11:00" 视为一个小时时间范围内，而 "23:51" - "00:10" 不被视为一小时内，因为系统记录的是某一天内的使用情况。
 */
public class Solution {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        if (keyName == null || keyName.length == 0) {
            return new ArrayList<>();
        }
        int n = keyName.length;
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            map.putIfAbsent(name, new ArrayList<>());
            map.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        for (String name : map.keySet()) {
            List<Integer> list = map.get(name);
            Collections.sort(list);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                int start = list.get(i - 2);
                int end = list.get(i);
                if (end - start <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }

}
