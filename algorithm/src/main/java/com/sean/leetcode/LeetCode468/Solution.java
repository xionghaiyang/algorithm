package com.sean.leetcode.LeetCode468;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-08 16:46
 * @Description: https://leetcode.cn/problems/validate-ip-address
 * 468. 验证IP地址
 * 给定一个字符串 queryIP。
 * 如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。
 * 其中 0 <= xi <= 255 且 xi 不能包含 前导零。
 * 例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 */
public class Solution {

    public String validIPAddress(String queryIP) {
        if (queryIP.indexOf(".") >= 0 && check4(queryIP)) {
            return "IPv4";
        }
        if (queryIP.indexOf(":") >= 0 && check6(queryIP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean check4(String queryIP) {
        int n = queryIP.length(), cnt = 0;
        char[] ip = queryIP.toCharArray();
        for (int i = 0; i < n && cnt < 4; ) {
            //找到连续数字段，以x存储
            int j = i, x = 0;
            while (j < n && ip[j] >= '0' && ip[j] <= '9' && x <= 255) {
                x = x * 10 + (ip[j++] - '0');
            }
            //非item字符之间没有item
            if (i == j) {
                return false;
            }
            //含前导零或数值大于255
            if ((j - i > 1 && ip[i] == '0') || x > 255) {
                return false;
            }
            i = j + 1;
            if (j == n) {
                break;
            }
            if (ip[j] != '.') {
                return false;
            }
            cnt++;
        }
        return cnt == 3 && ip[0] != '.' && ip[n - 1] != '.';
    }

    private boolean check6(String queryIP) {
        int n = queryIP.length(), cnt = 0;
        char[] ip = queryIP.toCharArray();
        for (int i = 0; i < n && cnt < 8; ) {
            int j = i;
            while (j < n && ((ip[j] >= 'a' && ip[j] <= 'f') || (ip[j] >= 'A' && ip[j] <= 'F') || (ip[j] >= '0' && ip[j] <= '9'))) {
                j++;
            }
            //非item字符之间没有item或者长度超过4
            if (i == j || j - i > 4) {
                return false;
            }
            i = j + 1;
            if (j == n) {
                break;
            }
            //存在除:以外的其他非数字字符
            if (ip[j] != ':') {
                return false;
            }
            cnt++;
        }
        return cnt == 7 && ip[0] != ':' && ip[n - 1] != ':';
    }

}
