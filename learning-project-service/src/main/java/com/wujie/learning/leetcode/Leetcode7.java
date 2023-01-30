package com.wujie.learning.leetcode;

/**
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *  
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode7 {

    public static int reverse(int x) {
        String prefix = "";
        if (x == 0) {
            return 0;
        } else if (x < 0) {
            prefix = "-";
            x = x * (-1);
        }
        String var1 = x + "";
        String[] stack = new String[32];
        for (int i = 0; i < var1.length(); i++) {
            stack[i] = var1.substring(i, i + 1);
        }
        String result = "";
        for (int i = 0; i < var1.length(); i++) {
            result = stack[i] + result;
        }
        while (result.indexOf("0") == 0) {
            result = result.substring(1);
        }
        int j = Integer.parseInt(prefix + result);
        int k = (int) Math.pow(2, 31);
        if (k*(-1) <= j && j <= (k - 1)) {
            return j;
        }
        return 0;
    }

    public static int reverseNew(int x) {
        String str = x + "";
        boolean flag = str.charAt(0) == '-';
        if (flag) str = str.substring(1);
        int size = str.length();
        long result = 0L;
        for (int i = size - 1; i >= 0; i--) {
            result = 10 * result + (str.charAt(i) - '0');
        }
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) return  0;
        return flag ? (int) -result : (int) result;
    }

    public static void main(String[] args) {
        System.out.println(reverseNew(-123065346));
        System.out.println(reverseNew(3450000));
        System.out.println(reverseNew(4600345));
        System.out.println(reverseNew(0));
    }
}
