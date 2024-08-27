package com.sean.leetcode.LeetCode690;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-08-27 10:03
 * @Description https://leetcode.cn/problems/employee-importance/description/
 * 690. 员工的重要性
 * 你有一个保存员工信息的数据结构，它包含了员工唯一的 id ，重要度和直系下属的 id 。
 * 给定一个员工数组 employees，其中：
 * employees[i].id 是第 i 个员工的 ID。
 * employees[i].importance 是第 i 个员工的重要度。
 * employees[i].subordinates 是第 i 名员工的直接下属的 ID 列表。
 * 给定一个整数 id 表示一个员工的 ID，返回这个员工和他所有下属的重要度的 总和。
 */
public class Solution {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return process(map, id);
    }

    private int process(Map<Integer, Employee> map, int id) {
        Employee employee = map.get(id);
        int res = employee.importance;
        for (int subordinate : employee.subordinates) {
            res += process(map, subordinate);
        }
        return res;
    }


}
