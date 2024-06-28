package com.sean.base.chapter11;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-25 21:41
 * @Description: 实现二叉树的序列化和反序列化
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 但是无法通过中序遍历的方式实现序列化和反序列化，
 * 因为不同的两棵树，可能得到相同的中序序列
 */
public class Code02_SerializeAndReconstructTree {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

    }

    public Queue<String> preSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        pres(head, res);
        return res;
    }

    private void pres(Node head, Queue<String> res) {
        if (head == null) {
            res.offer(null);
        } else {
            res.offer(String.valueOf(head.value));
            pres(head.left, res);
            pres(head.right, res);
        }
    }

    public Queue<String> inSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        ins(head, res);
        return res;
    }

    private void ins(Node head, Queue<String> res) {
        if (head == null) {
            res.offer(null);
        } else {
            ins(head.left, res);
            res.offer(String.valueOf(head.value));
            ins(head.right, res);
        }
    }

    public Queue<String> posSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        poss(head, res);
        return res;
    }

    private void poss(Node head, Queue<String> res) {
        if (head == null) {
            res.offer(null);
        } else {
            poss(head.left, res);
            poss(head.right, res);
            res.offer(String.valueOf(head.value));
        }
    }

    public Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.isEmpty()) {
            return null;
        }
        return preb(preList);
    }

    private Node preb(Queue<String> preList) {
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    public Node buildByPosQueue(Queue<String> posList) {
        if (posList == null || posList.isEmpty()) {
            return null;
        }
        //左右中 -> stack(中右左)
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()) {
            stack.push(posList.poll());
        }
        return posb(stack);
    }

    private Node posb(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }

    public Queue<String> levelSerial(Node head) {
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

    public Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    private Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
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

    public boolean isSameValueStructure(Node head1, Node head2) {
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        Code02_SerializeAndReconstructTree solution = new Code02_SerializeAndReconstructTree();
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = solution.generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = solution.preSerial(head);
            Queue<String> pos = solution.posSerial(head);
            Queue<String> level = solution.levelSerial(head);
            Node preBuild = solution.buildByPreQueue(pre);
            Node posBuild = solution.buildByPosQueue(pos);
            Node levelBuild = solution.buildByLevelQueue(level);
            if (!solution.isSameValueStructure(posBuild, preBuild) || !solution.isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
