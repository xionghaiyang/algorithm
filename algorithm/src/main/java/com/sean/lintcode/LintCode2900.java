package com.sean.lintcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class LintCode2900 {

    public static LinkedList<String> Weighting(LinkedList<String> list) {
        Set<String> set = new HashSet<>();
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String next = iter.next();
            if (set.contains(next)) {
                iter.remove();
            } else {
                set.add(next);
            }
        }
        return list;
    }

}
