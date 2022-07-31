package com.sean.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode929 {

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            int index = email.indexOf("@");
            String local = email.substring(0, index)
                    .split("\\+")[0]
                    .replace(".", "");
            set.add(local + email.substring(index));
        }
        return set.size();
    }

}
