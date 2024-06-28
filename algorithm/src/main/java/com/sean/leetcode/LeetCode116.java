package com.sean.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
public class LeetCode116 {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    //层次遍历
    public static Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        //初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        //外层的while循环迭代的时层数
        while (!queue.isEmpty()) {
            //记录当前队列大小
            int size = queue.size();
            //遍历这一层的所有节点
            for (int i = 0; i < size; i++) {
                //从队首取出元素
                Node node = queue.poll();
                //连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                //拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        //返回根节点
        return root;
    }

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node leftmost = root;

        while (leftmost.left != null) {
            //遍历这一层节点组织成的链表，为下一层的节点更新next指针
            Node head = leftmost;
            while (head != null) {
                //CONNECTION1
                head.left.next = head.right;
                //CONNECTION2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                //指针向后移动
                head = head.next;
            }
            //去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }

}
