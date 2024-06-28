package com.sean.base.chapter27;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-23 20:03
 * @Description: TODO
 */
public class Code02_TreeEqual {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    private boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    public boolean containsTree2(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        List<String> b = preSerial(big);
        List<String> s = preSerial(small);
        String[] str = new String[b.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = b.get(i);
        }
        String[] match = new String[s.size()];
        for (int i = 0; i < match.length; i++) {
            match[i] = s.get(i);
        }
        return getIndexOf(str, match) != -1;
    }

    private List<String> preSerial(Node head) {
        List<String> res = new ArrayList<>();
        pres(head, res);
        return res;
    }

    private void pres(Node head, List<String> res) {
        if (head == null) {
            res.add(null);
        } else {
            res.add(String.valueOf(head.value));
            pres(head.left, res);
            pres(head.right, res);
        }
    }

    private int getIndexOf(String[] st1, String[] str2) {
        if (st1 == null || str2 == null || str2.length < 1 || st1.length < str2.length) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < st1.length && y < str2.length) {
            if (isEqual(st1[x], str2[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    private int[] getNextArray(String[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (isEqual(ms[i - 1], ms[cn])) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    private boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    public Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        Code02_TreeEqual solution = new Code02_TreeEqual();
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTimes; i++) {
            Node big = solution.generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = solution.generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean res1 = solution.containsTree1(big, small);
            boolean res2 = solution.containsTree2(big, small);
            if (res1 != res2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish!");
    }

}
