package com.youlianmei.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
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
