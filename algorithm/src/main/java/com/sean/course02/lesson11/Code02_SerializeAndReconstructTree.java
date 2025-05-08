package com.sean.course02.lesson11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author xionghaiyang
 * @Date 2025-05-08 19:03
 * @Description 序列化与反序列化二叉树
 */
public class Code02_SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Queue<String> preSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        pres(head, res);
        return res;
    }

    private static void pres(Node head, Queue<String> res) {
        if (head == null) {
            res.offer(null);
        } else {
            res.offer(String.valueOf(head.value));
            pres(head.left, res);
            pres(head.right, res);
        }
    }

    public static Node buildByPreQueue(Queue<String> pre) {
        if (pre == null || pre.size() == 0) {
            return null;
        }
        return preb(pre);
    }

    private static Node preb(Queue<String> pre) {
        String value = pre.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(pre);
        head.right = preb(pre);
        return head;
    }

    //二叉树无法通过中序遍历的方式实现序列化和反序列化
    //例如:
    //             1
    //               \
    //               2
    //和           2
    //           /
    //         1
    //补足空位置的中序遍历结果都是{null , 1 , null , 2 , null}
    public static Queue<String> inSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        ins(head, res);
        return res;
    }

    private static void ins(Node head, Queue<String> res) {
        if (head == null) {
            res.offer(null);
        } else {
            ins(head.left, res);
            res.offer(String.valueOf(head.value));
            ins(head.right, res);
        }
    }

    public static Queue<String> posSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        poss(head, res);
        return res;
    }

    private static void poss(Node head, Queue<String> res) {
        if (head == null) {
            res.add(null);
        } else {
            poss(head.left, res);
            poss(head.right, res);
            res.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPosQueue(Queue<String> pos) {
        if (pos == null || pos.size() == 0) {
            return null;
        }
        //左右中 -> stack(中右左)
        Stack<String> stack = new Stack<>();
        while (!pos.isEmpty()) {
            stack.push(pos.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> pos) {
        String value = pos.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(pos);
        head.left = posb(pos);
        return head;
    }

    public static Queue<String> levelSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        if (head == null) {
            res.offer(null);
        } else {
            res.offer(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    res.offer(String.valueOf(head.left.value));
                    queue.offer(head.left);
                } else {
                    res.offer(null);
                }
                if (head.right != null) {
                    res.offer(String.valueOf(head.right.value));
                    queue.offer(head.right);
                } else {
                    res.offer(null);
                }
            }
        }
        return res;
    }

    public static Node buildByLevelQueue(Queue<String> level) {
        if (level == null || level.size() == 0) {
            return null;
        }
        Node head = generateNode(level.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(level.poll());
            node.right = generateNode(level.poll());
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static Node generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }

    private static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (maxValue * Math.random()));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    private static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null ^ head2 == null) {
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

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num; i++) {
            res.append(space);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(posBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
