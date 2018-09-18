package com.flower.mine.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date truncateToDay(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }
}
