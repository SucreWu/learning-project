package com.wujie.learning.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author wujie
 * @Description
 * @date 2023/2/8 16:26
 */
public class NumberFormatUtil {

    /**
     * 移除数字前面和后面冗余的0，只保留3位小数
     *
     * @param isFormat 是否需要千位分隔符(,)这种格式输出
     * @param num
     * @return
     */
    public static String trim0(boolean isFormat, double num) {
        NumberFormat nf = NumberFormat.getInstance();
        if (!isFormat) {
            //设置输出格式是否使用“,”分组,默认是使用的
            nf.setGroupingUsed(false);
        }
        String result = nf.format(num);
//        return isFormat ? result : result.replace(",", ""); //用上面代码替换去除分隔符操作
        return result;
    }

    /**
     * 移除数字前面和后面冗余的0
     *
     * @param isFormat      是否需要千位分隔符(,)这种格式输出
     * @param num
     * @param fractionDigit 要保留的小数位数
     * @return
     */
    public static String trim0(boolean isFormat, double num, int fractionDigit) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(fractionDigit);
        //setMaximumFractionDigits不会保留小数点和后面多余的0，不需下面正则去除
//        if (result.contains(".") && result.endsWith("0")) {
//            result = result.replaceAll("0+?$", "");//去掉多余的0
//            result = result.replaceAll("[.]$", "");//如最后一位是.则去掉
//        }
        if (!isFormat) {
            //设置输出格式是否使用“,”分组,默认是使用的
            nf.setGroupingUsed(false);
        }
        String result = nf.format(num);
//        return isFormat ? result : result.replace(",", "");
        return result;
    }

    /**
     * 指定位数输出，不足补0
     * 整数部分如果位数大于需要的位数按实际位数输出
     * 小数部分如果大于需要的位数四舍五入
     *
     * @param num
     * @param integerDigit  整数部分位数
     * @param fractionDigit 小数部分位数
     * @return
     */
    public static String add0Format(double num, int integerDigit, int fractionDigit) {
        StringBuilder rule = new StringBuilder();
        if (integerDigit <= 0) {
            rule.append("#");
        } else {
            for (int i = 0; i < integerDigit; i++) {
                rule.append("0");
            }
        }
        if (fractionDigit > 0) {
            rule.append(".");
            for (int i = 0; i < fractionDigit; i++) {
                rule.append("0");
            }
        }
        DecimalFormat df = new DecimalFormat(rule.toString());
        return df.format(num);
    }

    /**
     * 保留几位小数，不足不补0，小数部分冗余的0也不显示
     *
     * @param num
     * @param fractionDigit 要保留小数的位数
     * @return
     */
    public static String fractionDigitFormat(double num, int fractionDigit) {
        /*方法一*/
//        StringBuilder rule = new StringBuilder("#");
//        if (fractionDigit > 0) {
//            rule.append(".");
//            for (int i = 0; i < fractionDigit; i++) {
//                rule.append("#");
//            }
//        }
//        DecimalFormat df = new DecimalFormat(rule.toString());
//        return df.format(num);

        /*方法二*/
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(fractionDigit);
        //设置输出格式是否使用“,”分组,这里不使用
        nf.setGroupingUsed(false);
        return nf.format(num);
    }

    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumIntegerDigits(3);
        numberFormat.setMaximumIntegerDigits(3);
        double dd = 121414.8293749;
        System.out.println(numberFormat.format(dd));
    }
}
