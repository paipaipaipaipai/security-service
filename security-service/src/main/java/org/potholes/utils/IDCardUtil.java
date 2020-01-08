package org.potholes.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDCardUtil {

    private static Logger logger = LoggerFactory.getLogger(IDCardUtil.class);

    /**
     *  根据身份证号码解析出生日期
     *  18位的居民身份证号:十七位数字本体码和一位校验码组成;排列顺序从左至右依次为：六位数字地址码,八位数字出生日期码,三位数字顺序码(顺序码的奇数分配给男性,偶数分配给女性),一位数字校验码
     *  15位的居民身份证号:1-2位省、自治区、直辖市代码;3-4位地级市、盟、自治州代码;5-6位县、县级市、区代码;
     *      7-12位出生年月日(比如670401代表1967年4月1日);13-15位为顺序号,其中15位男为单数,女为双数
     * @param cardNo 身份证号码
     * @return 出生日期 yyyy-MM-dd
     */
    public static String analyseBirthdayByCardNo(String cardNo) {
        String birthday = null;
        try {
            if (cardNo.length() == 18) {
                // 身份证的长度是18位,第7到14位是出生日期
                birthday = cardNo.substring(6, 14);
            } else {
                // 身份证的长度是15位,第7到12位是出生日期
                birthday = "19" + cardNo.substring(6, 14);
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            sdf1.setLenient(false);
            Date newDate = sdf1.parse(birthday);
            birthday = sdf2.format(newDate);
        } catch (Exception e) {
            logger.error("身份证号码解析出生日期失败....cardNo={}", cardNo);
        }
        return birthday;
    }

    /**
     *  根据身份证号码计算年龄
     *  18位的居民身份证号:十七位数字本体码和一位校验码组成;排列顺序从左至右依次为：六位数字地址码,八位数字出生日期码,三位数字顺序码(顺序码的奇数分配给男性,偶数分配给女性),一位数字校验码
     *  15位的居民身份证号:1-2位省、自治区、直辖市代码;3-4位地级市、盟、自治州代码;5-6位县、县级市、区代码;
     *      7-12位出生年月日(比如670401代表1967年4月1日);13-15位为顺序号,其中15位男为单数,女为双数
     * @param cardNo 身份证号码
     * @return 年龄
     */
    public static Integer analyseAgeByCardNo(String cardNo) {
        Integer age = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YMD);
            if (cardNo.length() == 18) {
                // 身份证的长度是18位,第7到14位是出生日期
                String birthdayStr = cardNo.substring(6, 14);
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int year = Integer.parseInt(birthdayStr.substring(0, 4));
                // 年龄=当前年份-出生年份
                int num = currentYear - year;
                calendar.setTime(sdf.parse(birthdayStr));
                calendar.add(Calendar.YEAR, num);
                Date birthdayAdded = calendar.getTime();
                if (birthdayAdded.after(new Date())) {
                    num--;
                }
                age = num;
            } else {
                // 身份证的长度是15位,第7到12位是出生日期
                String birthdayStr = "19" + cardNo.substring(6, 14);
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int year = Integer.parseInt(birthdayStr.substring(0, 4));
                // 年龄=当前年份-出生年份
                int num = currentYear - year;
                calendar.setTime(sdf.parse(birthdayStr));
                calendar.add(Calendar.YEAR, num);
                Date birthdayAdded = calendar.getTime();
                if (birthdayAdded.after(new Date())) {
                    num--;
                }
                age = num;
            }
        } catch (Exception e) {
            logger.error("身份证号码解析年龄失败....cardNo={}", cardNo);
        }
        return age;
    }

    /**
     *  根据身份证号码计算性别
     *  18位的居民身份证号:十七位数字本体码和一位校验码组成;排列顺序从左至右依次为：六位数字地址码,八位数字出生日期码,三位数字顺序码(顺序码的奇数分配给男性,偶数分配给女性),位数字校验码
     *  15位的居民身份证号:1-2位省、自治区、直辖市代码;3-4位地级市、盟、自治州代码;5-6位县、县级市、区代码;
     *      7-12位出生年月日(比如670401代表1967年4月1日);13-15位为顺序号,其中15位男为单数,女为双数
     * @param cardNo 身份证号码
     * @return 性别
     */
    public static String analyseSexByCardNo(String cardNo) {
        String sex = null;
        try {
            if (cardNo.length() == 18) {
                String code = cardNo.substring(14, 17);
                if (Integer.valueOf(code) % 2 == 0)
                    sex = "女";// 偶数分配给女性
                else
                    sex = "男";// 奇数分配给男性
            } else {
                String code = cardNo.substring(14, 15);
                if (Integer.valueOf(code) % 2 == 0)
                    sex = "女";// 偶数分配给女性
                else
                    sex = "男";// 奇数分配给男性
            }
        } catch (Exception e) {
            logger.error("身份证号码解析性别失败....cardNo={}", cardNo);
        }
        return sex;
    }

}
