package com.liziczh.erp.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author Bruce
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static Format decimalFormat = new DecimalFormat("00");

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyyMMdd", "yyyyMMdd HHmmss", "yyyyMMdd HHmm", "yyyyMM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 获取前N天的日期
     *
     * @param date
     * @return
     */
    public static String getBeforeDay(Date date, Integer offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return formatDate(calendar.getTime(), "yyyyMMdd");
    }

    /**
     * 获取前N天的日期
     *
     * @param date
     * @return
     */
    public static String getBeforeDay(Date date, Integer offset, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return formatDate(calendar.getTime(), dateFormat);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static Long getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取当前日期所在周的第一天(周一)
     *
     * @param day
     * @return Date
     */
    public static Date getFirstDayOfWeek(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DATE, -dayOfWeek + 1);
        return calendar.getTime();
    }

    /**
     * 获取当前日期所在周的最后天(周末)
     *
     * @return Date
     */
    public static Date getLastDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        calendar.add(Calendar.DATE, -dayOfWeek + 7);
        return calendar.getTime();
    }

    /**
     * 获取当前日期所在周的最后天(周末)
     *
     * @return Date
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在的周末
     *
     * @param time
     * @return
     */
    public static String getLastDayOfWeek(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, 6);
        return sdf.format(cal.getTime()).replace("-", "");
    }

    /**
     * 获取指定日期的月初第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }


    /**
     * 判断日期是否为月末
     *
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断日期day所在年的 第一天 是否为周一
     *
     * @param day
     * @return true or false
     */
    public static Boolean firstDayOfYearIsMonday(String day) {
        if (StringUtils.isNotEmpty(day)) {
            String year = day.substring(0, 4) + "0101";
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate(year));
            return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ? true : false;
        } else {
            return false;
        }
    }

    /**
     * 返回日期day所在周 在当年的周数(所在年第一天不是周一则计入上一年的周数中,从下一周开始计数)
     *
     * @param day
     * @return true or false
     */
    public static String getWeekNum(String day) {
        if (StringUtils.isNotEmpty(day)) {
            Integer year = Integer.parseInt(day.substring(0, 4));
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(parseDate(day));
            if (firstDayOfYearIsMonday(day)) {//一年的第一天是周一
                return year + decimalFormat.format(calendar.get(Calendar.WEEK_OF_YEAR));
            } else {
                if (isSameWeek(year + "0101", day)) { //则为上年的最后一周
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                    return (year - 1) + decimalFormat.format(calendar.get(Calendar.WEEK_OF_YEAR));
                } else if (isSameWeek((year + 1) + "0101", day)) {
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                    return year + decimalFormat.format(calendar.get(Calendar.WEEK_OF_YEAR));
                } else {
                    return year + decimalFormat.format(calendar.get(Calendar.WEEK_OF_YEAR) - 1); //其它情况则为计算结果减1
                }
            }
        } else {
            return null;
        }
    }

    /**
     * 返判断两个日期是否在同一周
     *
     * @param first, second
     * @return true or false
     */
    public static Boolean isSameWeek(String first, String second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        calendar.setTime(parseDate(first));
        Integer firstYear = calendar.get(Calendar.YEAR);
        Integer firstWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        Integer firstMonth = calendar.get(Calendar.MONTH);
        Integer firstDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(parseDate(second));
        Integer secondYear = calendar.get(Calendar.YEAR);
        Integer secondWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        Integer secondMonth = calendar.get(Calendar.MONTH);
        Integer secondDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        Integer subYear = firstYear - secondYear;
        if (subYear == 0) {
            if ((firstWeek.equals(secondWeek) && (secondDayOfYear - firstDayOfYear) < 7)) {
                return true;
            }
        } else if (subYear == 1 && secondMonth == 11) {
            if (firstWeek.equals(secondWeek)) {
                return true;
            }
        } else if (subYear == -1 && firstMonth == 11) {
            if (firstWeek.equals(secondWeek)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

//		System.out.println( getFirstDayOfWeek(new Date()) );
    }
}