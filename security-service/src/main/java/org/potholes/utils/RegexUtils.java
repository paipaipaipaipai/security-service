package org.potholes.utils;

import java.util.regex.Pattern;

public class RegexUtils {

    /***
     * 英文字母大写
     * @param str
     * @return
     */
    public static boolean capitalLetter(String str) {
        return Pattern.matches("^[A-Za-z]+$", str);
    }

    /***
     * 中文,字母,数字
     * @param str
     * @return
     */
    public static boolean commonString(String str) {
        return Pattern.matches("^[A-Za-z0-9\u4e00-\u9fa5]+$", str);
    }

    /***
     * 身份证
     * @param str
     * @return
     */
    public static boolean idCard(String str) {
        return Pattern.matches("[1-9]\\d{13,16}[a-zA-Z0-9]{1}", str);
    }

}
