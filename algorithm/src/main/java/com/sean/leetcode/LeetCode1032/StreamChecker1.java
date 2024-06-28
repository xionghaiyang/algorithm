package com.sean.leetcode.LeetCode1032;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-24 08:54
 * @Description: TODO
 */
class StreamChecker1 {

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        TrieNode fail;

        public TrieNode() {
            children = new TrieNode[26];
        }

        public TrieNode getChild(int index) {
            return children[index];
        }

        public void setChild(int index, TrieNode node) {
            children[index] = node;
        }

        public boolean getIsEnd() {
            return isEnd;
        }

        public void setIsEnd(boolean b) {
            isEnd = b;
        }

        public TrieNode getFail() {
            return fail;
        }

        public void setFail(TrieNode node) {
            fail = node;
        }

    }

    TrieNode root;
    TrieNode temp;

    public StreamChecker1(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.getChild(index) == null) {
                    cur.setChild(index, new TrieNode());
                }
                cur = cur.getChild(index);
            }
            cur.setIsEnd(true);
        }
        root.setFail(root);
        Queue<TrieNode> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (root.getChild(i) != null) {
                root.getChild(i).setFail(root);
                queue.add(root.getChild(i));
            } else {
                root.setChild(i, root);
            }
        }
        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();
            node.setIsEnd(node.getIsEnd() || node.getFail().getIsEnd());
            for (int i = 0; i < 26; i++) {
                if (node.getChild(i) != null) {
                    node.getChild(i).setFail(node.getFail().getChild(i));
                    queue.offer(node.getChild(i));
                } else {
                    node.setChild(i, node.getFail().getChild(i));
                }
            }
        }
        temp = root;
    }

    public boolean query(char letter) {
        temp = temp.getChild(letter - 'a');
        return temp.getIsEnd();
    }
}
