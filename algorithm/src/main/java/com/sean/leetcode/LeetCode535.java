package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode535 {

    public class Codec {

        private Map<Integer, String> map = new HashMap<Integer, String>();
        private int id = 0;

        public String encode(String longUrl) {
            id++;
            map.put(id, longUrl);
            return "http://tinyurl.com/" + id;
        }

        public String decode(String shortUrl) {
            int p = shortUrl.lastIndexOf("/") + 1;
            int key = Integer.parseInt(shortUrl.substring(p));
            return map.get(key);
        }

    }

}
