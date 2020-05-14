package com.gideon.sms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author GideonYeung
 **/
public class RegexUtil {

    /**
     * 简单手机号码正则表达式。
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 确切的手机号码正则表达式。
     * <p>中国移动: 134(0-8), 135, 136, 137, 138, 139, 147, 150, 151, 152, 157, 158, 159, 178, 182, 183, 184,
     * 187, 188, 198</p>
     * <p>中国联通: 130, 131, 132, 145, 155, 156, 166, 171, 175, 176, 185, 186</p>
     * <p>中国电信: 133, 153, 173, 177, 180, 181, 189, 199</p>
     * <p>卫星电话: 1349</p>
     * <p>虚拟运营商: 170</p>
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|" +
            "(18[0-9])|(19[8,9]))\\d{8}$";

    /**
     * 姓名正则表达式
     */
    public static final String FULL_NAME = "[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*";

    /**
     * 电话号码的正则表达式。
     */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * 身份证号码正则表达式，长度为15。
     */
    public static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 匹配18位身份证号
     */
    public static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}" +
            "([0-9Xx])$";
    /**
     * 电子邮件正则表达式。
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 网址的正则表达式。
     */
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
    /**
     * 汉字正则表达式。
     */
    public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";
    /**
     * 用户名的正则表达式。
     * <p>适用范围 "a-z", "A-Z", "0-9", "_", "汉字"</p>
     * <p>不能以"_"结尾</p>
     * <p>长度在6到20之间</p>
     */
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     * 日期的正则表达式，其格式为“ yyyy-MM-dd”
     */
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|" +
            "(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|" +
            "(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     * ip地址的正则表达式。
     */
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    ///////////////////////////////////////////////////////////////////////////
    // 以下来自http://tool.oschina.net/regex
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 匹配双字节字符(包括汉字在内)
     */
    public static final String REGEX_DOUBLE_BYTE_CHAR = "[^\\x00-\\xff]";
    /**
     * 匹配空白行
     */
    public static final String REGEX_BLANK_LINE = "\\n\\s*\\r";
    /**
     * 匹配腾讯QQ号
     */
    public static final String REGEX_QQ_NUM = "[1-9][0-9]{4,}";
    /**
     * 匹配中国邮政编码
     */
    public static final String REGEX_CHINA_POSTAL_CODE = "[1-9]\\d{5}(?!\\d)";
    /**
     * 匹配正整数
     */
    public static final String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";
    /**
     * 匹配负整数
     */
    public static final String REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$";
    /**
     * 匹配整数
     */
    public static final String REGEX_INTEGER = "^-?[1-9]\\d*$";
    /**
     * 匹配非负整数（正整数 + 0）
     */
    public static final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";
    /**
     * 匹配非正整数（负整数 + 0）
     */
    public static final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";
    /**
     * 匹配正浮点数
     */
    public static final String REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
    /**
     * 匹配负浮点数
     */
    public static final String REGEX_NEGATIVE_FLOAT = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";

    private RegexUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 空行校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isBlankLine(final CharSequence input) {
        return isMatch(REGEX_BLANK_LINE, input);
    }

    /**
     * 双字节字符校验(长度是两个字节(byte)的字符,比如“嗨”、“!”、“。”,汉字及中文标点就是双字节字符;“k”、“!”、“.”,英文字母及英文标点是单字节的。)。
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isDoubleByteChar(final CharSequence input) {
        return isMatch(REGEX_DOUBLE_BYTE_CHAR, input);
    }


    /**
     * 负浮点数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNegativeFloat(final CharSequence input) {
        return isMatch(REGEX_NEGATIVE_FLOAT, input);
    }

    /**
     * 正浮点数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPositiveFloat(final CharSequence input) {
        return isMatch(REGEX_POSITIVE_FLOAT, input);
    }

    /**
     * 非正整数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNotPositiveInteger(final CharSequence input) {
        return isMatch(REGEX_NOT_POSITIVE_INTEGER, input);
    }

    /**
     * 非负整数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNotNegativeInteger(final CharSequence input) {
        return isMatch(REGEX_NOT_NEGATIVE_INTEGER, input);
    }

    /**
     * 整数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isInteger(final CharSequence input) {
        return isMatch(REGEX_INTEGER, input);
    }

    /**
     * 负整数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isNegativeInteger(final CharSequence input) {
        return isMatch(REGEX_NEGATIVE_INTEGER, input);
    }

    /**
     * 正整数校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPositiveInteger(final CharSequence input) {
        return isMatch(REGEX_POSITIVE_INTEGER, input);
    }

    /**
     * 中国邮政编码检验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isChinaPostalCode(final CharSequence input) {
        return isMatch(REGEX_CHINA_POSTAL_CODE, input);
    }

    /**
     * QQ号码校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isQQNum(final CharSequence input) {
        return isMatch(REGEX_QQ_NUM, input);
    }

    /**
     * 匹配所有中文名称
     * 支持xxx·xxxx
     */
    public static boolean isFullName(final CharSequence input) {
        return isMatch(FULL_NAME, input);
    }

    /**
     * 简单手机号码校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMobileSimple(final CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 确切的手机号码校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMobileExact(final CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    /**
     * 电话号码的校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isTel(final CharSequence input) {
        return isMatch(REGEX_TEL, input);
    }

    /**
     * 身份证号码校验，长度为15。
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isIDCard15(final CharSequence input) {
        return isMatch(REGEX_ID_CARD15, input);
    }

    /**
     * 身份证号码校验，长度为18。
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isIDCard18(final CharSequence input) {
        return isMatch(REGEX_ID_CARD18, input);
    }

    /**
     * 电子邮件校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isEmail(final CharSequence input) {
        return isMatch(REGEX_EMAIL, input);
    }

    /**
     * 网址校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isURL(final CharSequence input) {
        return isMatch(REGEX_URL, input);
    }

    /**
     * 汉字校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isZh(final CharSequence input) {
        return isMatch(REGEX_ZH, input);
    }

    /**
     * 用户名的检验
     * <p>适用范围 "a-z", "A-Z", "0-9", "_", "汉字"</p>
     * <p>不能以"_"结尾</p>
     * <p>长度在6到20之间</p>
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isUsername(final CharSequence input) {
        return isMatch(REGEX_USERNAME, input);
    }

    /**
     * 日期的校验，其格式为“ yyyy-MM-dd”
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isDate(final CharSequence input) {
        return isMatch(REGEX_DATE, input);
    }

    /**
     * ip地址的校验
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isIP(final CharSequence input) {
        return isMatch(REGEX_IP, input);
    }

    /**
     * 对字符串和正则表达式匹配
     *
     * @param regex The regex.
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 根据正则表达式获取匹配的数据
     *
     * @param regex The regex.
     * @param input The input.
     * @return the list of input matches the regex
     */
    public static List<String> getMatches(final String regex, final CharSequence input) {
        if (input == null) {
            return Collections.emptyList();
        }
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * 根据正则表达式分割
     *
     * @param input The input.
     * @param regex The regex.
     * @return the array of strings computed by splitting input around matches of regex
     */
    public static String[] getSplits(final String input, final String regex) {
        if (input == null) {
            return new String[0];
        }
        return input.split(regex);
    }

    /**
     * Replace the first subsequence of the input sequence that matches the
     * regex with the given replacement string.
     *
     * @param input       The input.
     * @param regex       The regex.
     * @param replacement The replacement string.
     * @return the string constructed by replacing the first matching
     * subsequence by the replacement string, substituting captured
     * subsequences as needed
     */
    public static String getReplaceFirst(final String input,
                                         final String regex,
                                         final String replacement) {
        if (input == null) {
            return "";
        }
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * Replace every subsequence of the input sequence that matches the
     * pattern with the given replacement string.
     *
     * @param input       The input.
     * @param regex       The regex.
     * @param replacement The replacement string.
     * @return the string constructed by replacing each matching subsequence
     * by the replacement string, substituting captured subsequences
     * as needed
     */
    public static String getReplaceAll(final String input,
                                       final String regex,
                                       final String replacement) {
        if (input == null) {
            return "";
        }
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }
}

