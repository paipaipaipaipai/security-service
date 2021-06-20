package org.potholes.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Mr.x
 * @date 2021/06/20
 */
public class JDK8DateUtils {

    public static final String YMD = "yyyy-MM-dd";
    public static final String SDF = "yyyy-MM-dd HH:mm:ss";

    /***
     * 获取当前的时间戳
     * 
     * @return
     */
    public static Long getCurrentMilli() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    /***
     * 获取当前时间
     * 
     * @param format 时间格式
     * @return
     */
    public static String getCurrentDateTime(String format) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(fmt);
    }

    /***
     * 获取当前时间
     * 
     * @param zoneId 时区
     * @param format 时间格式
     * @return
     */
    public static String getCurrentDateTime(String zoneId, String format) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(zoneId));
        return dateTime.format(fmt);
    }

    /***
     * 计算日期加减
     * 
     * @param time 时间
     * @param format 格式
     * @param amountToAdd 增减天数
     * @param unit 时间单位
     * @return
     */
    public static String calculateDateTime(String time, String format, long amountToAdd, ChronoUnit unit) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        LocalDateTime start = LocalDateTime.parse(time, fmt);
        LocalDateTime to = start.plus(amountToAdd, unit);
        return to.format(fmt);
    }

    /***
     * 计算日期差(天)
     * 
     * Duration:计算带有时分秒的日期; Period:计算年月日的日期
     * 
     * @param startTime
     * @param endTime
     * @param format
     * @return
     */
    public static long getDuration(String startTime, String endTime, String format) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        LocalDateTime start = LocalDateTime.parse(startTime, fmt);
        LocalDateTime end = LocalDateTime.parse(endTime, fmt);
        Duration duration = Duration.between(start, end);
        return duration.toDays();
    }

    /***
     * 时区转换
     * 
     * @param time 时间
     * @param format 格式
     * @param fromZoneId 当前时区
     * @param toZoneId 目标时区
     * @return
     */
    public static String convertZoneId(String time, String format, String fromZoneId, String toZoneId) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SDF);
        LocalDateTime from = LocalDateTime.parse(time, fmt);
        LocalDateTime to =
            from.atZone(ZoneId.of(fromZoneId)).withZoneSameInstant(ZoneId.of(toZoneId)).toLocalDateTime();
        return to.format(fmt);
    }

    public static void main(String[] args) {
        String shanghai = "Asia/Shanghai";
        String los_Angeles = "America/Los_Angeles";

        System.out.println("时间戳=" + getCurrentMilli());
        System.out.println("当前时间=" + getCurrentDateTime(SDF));
        System.out.println("上海时间=" + getCurrentDateTime(shanghai, SDF));
        System.out.println("洛杉矶时间=" + getCurrentDateTime(los_Angeles, SDF));

        System.out.println("日期加减=" + calculateDateTime("2021-06-01 12:05:00", SDF, 1, ChronoUnit.YEARS));
        System.out.println("日期加减=" + calculateDateTime("2021-06-01 12:05:00", SDF, 1, ChronoUnit.DAYS));

        System.out.println("日期相差天数=" + getDuration("2021-06-01 12:05:00", "2021-06-02 12:05:00", SDF));

        System.out
            .println("时区转换=" + convertZoneId(getCurrentDateTime(SDF), SDF, "Asia/Shanghai", "America/Los_Angeles"));
    }

}
