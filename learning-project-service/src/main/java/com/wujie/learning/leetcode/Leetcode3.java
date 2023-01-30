package com.wujie.learning.leetcode;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 无重复字符的最长子串是 "b"，其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "wke"，其长度为 3。
 *      请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
 */
public class Leetcode3 {
    public static int lengthOfLongestSubstring(String s){
        int result = 0;
        for (int i = 0; i < s.length(); i++){
            HashMap<Integer, String> map = new HashMap<>();
            String firstElement = s.substring(i,i+1);
            map.put(i,firstElement);
            for (int j = i+1; j < s.length(); j++){
                String x = s.substring(j,j+1);
                if (map.containsValue(x)){
                    result = ((j-i) > result) ? (j-i) : result;
                    break;
                }
                map.put(j,x);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
