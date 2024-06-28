package com.sean.leetcode.LeetCode590;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-02-19 10:59
 * @Description: https://leetcode.cn/problems/n-ary-tree-postorder-traversal/
 * 590. N 叉树的后序遍历
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class Solution {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        process(root, res);
        return res;
    }

    private void process(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.children != null) {
            for (Node child : root.children) {
                process(child, res);
            }
        }
        res.add(root.val);
    }

    public List<Integer> postorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                res.add(node.val);
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;
    }

    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            if (node.children.size() == 0 || visited.contains(node)) {
                stack.pop();
                res.add(node.val);
                continue;
            }
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
            visited.add(node);
        }
        return res;
    }

}
