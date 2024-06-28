package com.sean.base.chapter11;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-26 20:20
 * @Description: 求二叉树某节点的中序后继节点
 */
public class Code06_SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }

    }

    public Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Code06_SuccessorNode solution = new Code06_SuccessorNode();
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + solution.getSuccessorNode(test));
    }

}
