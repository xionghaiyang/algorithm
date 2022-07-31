package com.sean.lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LintCode872 {

    class Trie {
        int val;
        List<Trie> children;

        public Trie(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Trie> map = new HashMap<>();
        for (int id : pid) {
            Trie trie = new Trie(id);
            map.put(id, trie);
        }
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                Trie cur = map.get(ppid.get(i));
                cur.children.add(map.get(pid.get(i)));
            }
        }
        List<Integer> res = new ArrayList<>();
        res.add(kill);
        getAllChildren(map.get(kill), res);
        return res;
    }

    private void getAllChildren(Trie trie, List<Integer> list) {
        for (Trie child : trie.children) {
            list.add(child.val);
            getAllChildren(child, list);
        }
    }

}
