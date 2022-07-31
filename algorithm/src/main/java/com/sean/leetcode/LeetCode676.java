package com.sean.leetcode;

public class LeetCode676 {

    class Trie {
        private boolean isFinished;
        private Trie[] child;

        public Trie() {
            this.isFinished = false;
            this.child = new Trie[26];
        }

        public boolean isFinished() {
            return isFinished;
        }

        public void setFinished(boolean finished) {
            isFinished = finished;
        }

        public Trie[] getChild() {
            return child;
        }
    }

    class MagicDictionary {

        Trie root;

        public MagicDictionary() {
            root = new Trie();
        }

        public void buildDict(String[] dictionary) {
            for (String str : dictionary) {
                Trie cur = root;
                for (int i = 0; i < str.length(); i++) {
                    int index = str.charAt(i) - 'a';
                    if (cur.getChild()[index] == null) {
                        cur.getChild()[index] = new Trie();
                    }
                    cur = cur.getChild()[index];
                }
                cur.setFinished(true);
            }
        }

        public boolean search(String searchWord) {
            return dfs(searchWord, root, 0, false);
        }

        private boolean dfs(String searchWord, Trie node, int pos, boolean modified) {
            if (pos == searchWord.length()) {
                return node.isFinished() && modified;
            }
            int index = searchWord.charAt(pos) - 'a';
            if (node.getChild()[index] != null) {
                if (dfs(searchWord, node.getChild()[index], pos + 1, false)) {
                    return true;
                }
            }
            if (!modified) {
                for (int i = 0; i < 26; i++) {
                    if (i != pos && node.getChild()[i] != null) {
                        if (dfs(searchWord, node.getChild()[i], pos + 1, true)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

}
