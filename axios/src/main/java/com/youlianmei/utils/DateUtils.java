package com.youlianmei.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static Date getDate() {
        return new Date();
    }

    /**
     * 判断是否为null
     * @param date
     * @return
     */
    public static boolean dateIsNull(final Date date) {
        return date == null;
    }

    /**
     * 判断是否为null
     * @param date
     * @return
     */
    public static boolean dateIsNotNull(final Date date) {
        return !dateIsNull(date);
    }

    /**
     * date加一天
     * @param d
     * @return
     */
    public static Date dateAddOneDay(Date d) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
