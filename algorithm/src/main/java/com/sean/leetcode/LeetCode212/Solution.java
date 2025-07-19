package com.sean.leetcode.LeetCode212;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-07-19 18:31
 * @Description https://leetcode.cn/problems/word-search-ii
 * 212. 单词搜索 II
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 */
public class Solution {

    public class Trie {
        private Trie[] nexts;
        private int nextIndex;
        private boolean isEnd;

        public Trie() {
            nexts = new Trie[26];
            nextIndex = 0;
            isEnd = false;
        }

        public void add(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Trie();
                    cur.nextIndex |= (1 << index);
                }
                cur = cur.nexts[index];
            }
            cur.isEnd = true;
        }
    }

    private Set<String> set = new HashSet<>();
    private int m;
    private int n;
    private char[][] board;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Trie cur = trie;
                if ((cur.nextIndex & (1 << (board[i][j] - 'a'))) != 0) {
                    process(cur, i, j, new StringBuilder(), new boolean[m][n]);
                }
            }
        }
        return new ArrayList<>(set);
    }

    private void process(Trie cur, int i, int j, StringBuilder str, boolean[][] visited) {
        visited[i][j] = true;
        str.append(board[i][j]);
        cur = cur.nexts[board[i][j] - 'a'];
        if (cur.isEnd) {
            set.add(str.toString());
        }
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && (cur.nextIndex & (1 << (board[x][y] - 'a'))) != 0) {
                process(cur, x, y, str, visited);
                str.setLength(str.length() - 1);
                visited[x][y] = false;
            }
        }
    }

}
