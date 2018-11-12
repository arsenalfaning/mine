package com.flower.mine.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date truncateToDay(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    public static Date today() {
        return truncateToDay(new Date());
    }

    public static Date tomorrow() {
        return DateUtils.addDays(today(), 1);
    }

    public static Date yesterday() {
        return DateUtils.addDays(today(), -1);
    }

    public static Date lastMonth() {
        return DateUtils.addMonths(today(), -1);
    }
}
