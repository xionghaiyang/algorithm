package com.sean.leetcode.LeetCode126;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-04-03 19:41
 * @Description https://leetcode.cn/problems/word-ladder-ii
 * 126. 单词接龙 II
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。
 * 注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。
 * 请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。
 * 每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有单词 互不相同
 */
public class Solution {

    public class Node {
        private String word;
        private boolean isReal;
        private List<Node> neighbors;
        //0表示未遍历，1表示开始节点，2表示结束节点，奇数表示正方向遍历，偶数表示反方向遍历
        private int step = 0;
        //0表示未遍历，正数表示正方向遍历，负数表示负方向遍历
        private int flag = 0;
        //在最短路径上的步数，0表示不在最短路径上
        private int minPathStep = 0;

        public Node(String word, boolean isReal) {
            this.word = word;
            this.isReal = isReal;
            neighbors = new ArrayList<>();
        }

        //标记节点和它的来源节点为最短路径上的节点
        public void makeMinPath(int minPathStep) {
            //大于0表示已经在最短路径上
            if (this.minPathStep > 0) {
                return;
            }
            this.minPathStep = minPathStep;
            //1是开始节点，2是结束节点，表示已经到源头
            if (step <= 2) {
                return;
            }
            //来源邻接点
            int step = this.step - 2;
            for (Node neighbor : neighbors) {
                if (neighbor.step == step) {
                    neighbor.makeMinPath(minPathStep - this.flag);
                }
            }
        }

        public void getMinPaths(List<List<String>> res, List<String> path) {
            if (this.isReal) {
                path.add(this.word);
                if (this.step == 2) {
                    res.add(path);
                    return;
                }
            }
            int step = this.minPathStep + 2;
            for (Node neighbor : neighbors) {
                if (neighbor.minPathStep == step) {
                    neighbor.getMinPaths(res, new ArrayList<>(path));
                }
            }
        }
    }

    public class Graph {
        private Map<String, Node> graph;

        public Graph() {
            graph = new HashMap<>();
        }

        public Node add(String word) {
            Node node = new Node(word, true);
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                char c = str[i];
                str[i] = '*';
                String key = String.valueOf(str);
                if (!graph.containsKey(key)) {
                    graph.put(key, new Node(key, false));
                }
                graph.get(key).neighbors.add(node);
                node.neighbors.add(graph.get(key));
                str[i] = c;
            }
            return node;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord.equals(endWord)) {
            if (wordList.contains(endWord)) {
                res.add(Arrays.asList(beginWord));
            }
            return res;
        }
        if (beginWord.length() == 1) {
            if (wordList.contains(endWord)) {
                res.add(Arrays.asList(beginWord, endWord));
            }
            return res;
        }
        Graph graph = new Graph();
        Node endNode = null;
        for (String word : wordList) {
            Node node = graph.add(word);
            if (endNode == null && word.equals(endWord)) {
                endNode = node;
            }
        }
        if (endNode == null) {
            return res;
        }
        endNode.step = 2;
        endNode.flag = -2;
        Node beginNode = graph.add(beginWord);
        beginNode.step = 1;
        beginNode.flag = 2;
        int minStep = Integer.MAX_VALUE;
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.addLast(beginNode);
        linkedList.addLast(endNode);
        while (!linkedList.isEmpty()) {
            Node node = linkedList.removeFirst();
            if (node.step > minStep) {
                break;
            }
            for (Node neighbor : node.neighbors) {
                if (neighbor.step == 0) {
                    if (neighbor.neighbors.size() <= 1) {
                        continue;
                    }
                    neighbor.step = node.step + 2;
                    neighbor.flag = node.flag;
                    linkedList.addLast(neighbor);
                } else if (node.flag + neighbor.flag == 0) {
                    minStep = node.step;
                    if (node.flag > 0) {
                        node.makeMinPath(node.step);
                        neighbor.makeMinPath(node.step + 2);
                    } else {
                        neighbor.makeMinPath(neighbor.step);
                        node.makeMinPath(neighbor.step + 2);
                    }
                }
            }
        }
        if (minStep < Integer.MAX_VALUE) {
            beginNode.getMinPaths(res, new ArrayList<>());
        }
        return res;
    }

}
