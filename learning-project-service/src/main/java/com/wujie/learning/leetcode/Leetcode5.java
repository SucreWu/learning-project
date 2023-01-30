package com.wujie.learning.leetcode;

import java.util.Arrays;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode5 {

    public static boolean isPalindromic(String s){
        // 判断是否为回文串，从前往后和从后往前读内容一致
        int len = s.length();
        for (int i = 0; i < len / 2; i++){
            if (s.charAt(i) != s.charAt(len - i -1)){
                return false;
            }
        }
        return true;
    }

    public static String longestPalindrome(String s){
        String result = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j <= len; j++){
                String temp = s.substring(i, j);
                if (isPalindromic(temp) && (temp.length() > max)){
                    result = temp;
                    max = Math.max(max, temp.length());
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {
        String s1 = "abc";
        byte[] b1 = s1.getBytes();
        System.out.println(Arrays.toString(b1));
        System.out.println(b1[0] & 0xff);
        System.out.println(2 & 0xff);
        System.out.println(s1.charAt(s1.length()-1));


        String test = "ahdonggsjddjsridf";
        System.out.println(longestPalindrome(test));
    }
}
