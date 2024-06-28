package com.sean.base.chapter17;

import java.util.Stack;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-02 20:25
 * @Description: TODO
 */
public class Code02_Hanoi {

    public void hanoi1(int n) {
        leftToRight(n);
    }

    //请把1-N层圆盘 从左-->右
    private void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    //请把1-N层圆盘 从左-->中
    private void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    private void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    private void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    private void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    private void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    public void hanoi2(int n) {
        if (n > 0) {
            process(n, "left", "right", "mid");
        }
    }

    private void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            process(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            process(n - 1, other, to, from);
        }
    }

    public class Record {
        public boolean finish;
        public int n;
        public String from;
        public String to;
        public String other;

        public Record(boolean finish, int n, String from, String to, String other) {
            this.finish = finish;
            this.n = n;
            this.from = from;
            this.to = to;
            this.other = other;
        }
    }

    public void hanoi3(int n) {
        if (n < 1) {
            return;
        }
        Stack<Record> stack = new Stack<>();
        stack.push(new Record(false, n, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.n == 1) {
                System.out.println("Move 1 from " + cur.from + " to " + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish = true;
                }
            } else {
                if (!cur.finish) {
                    stack.push(cur);
                    stack.push(new Record(false, cur.n - 1, cur.from, cur.other, cur.to));
                } else {
                    System.out.println("Move " + cur.n + " from " + cur.from + " to " + cur.to);
                    stack.push(new Record(false, cur.n - 1, cur.other, cur.to, cur.from));
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        Code02_Hanoi solution = new Code02_Hanoi();
        solution.hanoi1(n);
        System.out.println("=====================");
        solution.hanoi2(n);
        System.out.println("======================");
        solution.hanoi3(n);
    }

}
