package com.sean.lintcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LintCode1082 {

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return getImportance(map, id);
    }

    public static int getImportance(Map<Integer, Employee> map, int pid) {
        int res = 0;
        Employee employee = map.getOrDefault(pid, null);
        if (employee != null) {
            res += employee.importance;
            for (int id : employee.subordinates) {
                res += getImportance(map, id);
            }
        }
        return res;
    }

}
