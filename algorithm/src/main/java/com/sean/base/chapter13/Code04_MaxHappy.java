package com.sean.base.chapter13;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-28 21:07
 * @Description: TODO
 */
public class Code04_MaxHappy {

    public class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int happy) {
            this.happy = happy;
            this.nexts = new ArrayList<>();
        }
    }

    public int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    //当前来到的节点叫cur
    //up表示cur的上级是否来
    //该函数含义:
    //如果up为true,表示cur的上级已经确定来的情况下,cur整棵树能够提供最大的快乐值是多少
    //如果up为false,表示cur的上级已经确定不来的情况下,cur整棵树能够提供的最大的快乐值是多少
    private int process1(Employee cur, boolean up) {
        if (up) {//如果cur的上级来的话,cur没得选，只能不来
            int res = 0;
            for (Employee next : cur.nexts) {
                res += process1(next, false);
            }
            return res;
        } else {//如果cur的上级不来的话,cur可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    public int maxHappy2(Employee head) {
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    public class Info {
        public int no;
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    private Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        int no = 0;
        int yes = x.happy;
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.no, nextInfo.yes);
            yes += nextInfo.no;
        }
        return new Info(no, yes);
    }

    public Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    public void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 1000000;
        boolean succeed = true;
        Code04_MaxHappy solution = new Code04_MaxHappy();
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            Employee boss = solution.genarateBoss(maxLevel, maxNexts, maxHappy);
            if (solution.maxHappy1(boss) != solution.maxHappy2(boss)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
