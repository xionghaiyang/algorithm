package com.sean.leetcode.LeetCode127;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-07-19 12:23
 * @Description https://leetcode.cn/problems/word-ladder
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。
 * 注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 */
public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Integer> queue = new LinkedList<>();
        int size = wordList.size();
        int[] dis = new int[size];
        Arrays.fill(dis, -1);
        boolean find = false;
        int target = -1;
        int turn = 2;
        for (int i = 0; i < size; i++) {
            if (endWord.equals(wordList.get(i))) {
                find = true;
                target = i;
            }
            if (valid(beginWord, wordList.get(i))) {
                queue.offer(i);
                dis[i] = turn;
            }
        }
        if (!find) {
            return 0;
        }
        while (!queue.isEmpty()) {
            int sz = queue.size();
            turn++;
            for (int i = 0; i < sz; i++) {
                int cur = queue.poll();
                if (cur == target) {
                    return dis[cur];
                }
                for (int j = 0; j < size; j++) {
                    if (dis[j] == -1 && valid(wordList.get(cur), wordList.get(j))) {
                        queue.offer(j);
                        dis[j] = turn;
                    }
                }
            }
        }
        return 0;
    }

    private boolean valid(String word1, String word2) {
        int res = 0;
        for (int i = 0; i < word1.length(); i++) {
            res += word1.charAt(i) != word2.charAt(i) ? 1 : 0;
        }
        return res == 1;
    }

    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int nodeNum = 0;

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(beginId);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int next : edge.get(x)) {
                if (dis[next] == Integer.MAX_VALUE) {
                    dis[next] = dis[x] + 1;
                    queue.offer(next);
                }
            }
        }
        return 0;
    }

    private void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] str = word.toCharArray();
        int n = str.length;
        for (int i = 0; i < n; i++) {
            char c = str[i];
            str[i] = '*';
            String newWord = String.valueOf(str);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            str[i] = c;
        }
    }

    private void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<>());
        }
    }

}
