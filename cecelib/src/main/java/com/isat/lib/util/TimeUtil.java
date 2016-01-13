package com.isat.lib.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import test.isat.com.cecelib.R;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
    public static final int DAY_TODAY = 0;
    public static final int DAY_YESTODAY = 1;
    public static final int DAY_BEFORE_YESTODAY = 2;
    public static final int DAY_UNKNOW = -1;

    private static ThreadLocal<DateFormat> DF_IN_HOUR_MIN = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_MONTH = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMM", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_DAY = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_DAY2 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_DAY3 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("MM/dd", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_DAY4 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_DAY_DDMM = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("MM-dd", Locale.getDefault());
        }
    };

    // private static final DateFormat DF_IN_MIN = new SimpleDateFormat(
    // "yyyyMMddHHmm");

    private static ThreadLocal<DateFormat> DF_IN_HOUR = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_HOUR2 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_MIN = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_MIN3 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_MIN4 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault());
        }
    };

    public static ThreadLocal<DateFormat> DF_IN_MIN5 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
    };

    // private static final DateFormat DF_IN_SEC = new SimpleDateFormat(
    // "yyyyMMddHHmmss");
    private static ThreadLocal<DateFormat> DF_IN_SEC = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_SEC2 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_SEC3 = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        }
    };

    private static ThreadLocal<DateFormat> DF_IN_MIN_CON = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        }
    };
    // private static final DateFormat DF_IN_MILLIS = new SimpleDateFormat(
    // "yyyyMMddHHmmssSSS");
    private static ThreadLocal<DateFormat> DF_IN_MILLIS = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS",
                    Locale.getDefault());
        }
    };

    public static DateFormat getDateFormatInHourMin() {
        return DF_IN_HOUR_MIN.get();
    }

    public static DateFormat getDateFormatInMonth() {
        return DF_IN_MONTH.get();
    }

    public static DateFormat getDateFormatInDay() {
        return DF_IN_DAY.get();
    }

    public static DateFormat getDateFormatInDay2() {
        return DF_IN_DAY2.get();
    }

    public static DateFormat getDateFormatInHour() {
        return DF_IN_HOUR.get();
    }

    public static DateFormat getDateFormatInHour2() {
        return DF_IN_HOUR2.get();
    }

    public static DateFormat getDateFormatInMin() {
        return DF_IN_MIN.get();
    }

    /**
     * 格式化器 yyyy-MM-dd HH:mm
     *
     * @return
     */
    public static DateFormat getDateFormatInMin3() {
        return DF_IN_MIN3.get();
    }

    public static DateFormat getDateFormatInMin4() {
        return DF_IN_MIN4.get();
    }

    public static DateFormat getDateFormatInSec() {
        return DF_IN_SEC2.get();
    }

    public static DateFormat getDateFormatInSec3() {
        return DF_IN_SEC3.get();
    }

    public static DateFormat getDateFormatInMillis() {
        return DF_IN_MILLIS.get();
    }

    public static String getTimeStrInDay(Date date) {
        return DF_IN_DAY.get().format(date);
    }

    public static String getTimeStrInDay2(Date date) {
        return DF_IN_DAY2.get().format(date);
    }

    public static String getTimeStrInDay3(Date date) {
        return DF_IN_DAY_DDMM.get().format(date);
    }

    public static String getTimeStrInHourMin(Date date) {
        return DF_IN_HOUR_MIN.get().format(date);
    }

    public static String getTimeStrInMin(Date date) {
        return DF_IN_MIN.get().format(date);
    }

    public static String getTimeStrInMin3(Date date) {
        return DF_IN_MIN3.get().format(date);
    }

    public static String getTimeStrInMin4(Date date) {
        return DF_IN_MIN4.get().format(date);
    }

    /**
     * 返回日期字符串yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getTimeStrInMin5(Date date) {
        return DF_IN_MIN5.get().format(date);
    }

    public static String getTimeStrInSec(Date date) {
        return DF_IN_SEC2.get().format(date);
    }

    public static String getTimeStrInSec() {
        Date date = new Date();
        return getTimeStrInSec(date);
    }

    public static long getMillisTime() {
        return new Date().getTime();
    }

    public static long getSecondTime() {
        return new Date().getTime();
    }

    public static String getTimeStrInMillis(Date date) {
        return DF_IN_MILLIS.get().format(date);
    }

    public static String getTimeStrInMillis() {
        Date date = new Date();
        return getTimeStrInMillis(date);
    }

    public static Calendar parseStrInMin(String source) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(DF_IN_MIN.get().parse(source));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return c;
    }

    public static Calendar parseStrInDay(String source) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(DF_IN_DAY.get().parse(source));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return c;
    }

    /**
     * 将字符串"yyyyMMddHHmmss"转换为日期格式
     *
     * @param source 字符串"yyyyMMddHHmmss"
     * @return
     */
    public static Calendar parseStrInSec(String source) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(DF_IN_SEC.get().parse(source));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return c;
    }

    /**
     * 将字符串"yyyy-MM-dd HH:mm:ss"转换为日期格式
     *
     * @param source 字符串"yyyyMMddHHmmss"
     * @return
     */
    public static Calendar parseStrInSec2(String source) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(DF_IN_SEC2.get().parse(source));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return c;
    }

    public static Calendar parseStrInMins(String source) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(DF_IN_MIN_CON.get().parse(source));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return c;
    }

    /**
     * 将字符串"yyyyMMddHHmmssSSS"转换为日期格式
     *
     * @param source 字符串"yyyyMMddHHmmssSSS"
     * @return
     */
    public static Calendar parseStrInMillis(String source) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(DF_IN_MILLIS.get().parse(source));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return c;
    }

    /**
     * 将时间转换为类似微信的时间， 当天显示具体MM:SS 昨天 显示昨天 前天到第10天显示 1天前至10天前，10天以前显示MM-DD
     *
     * @param date yyyyMMddHHmmss
     * @return
     */
    public static String generalParseDayBySec(String date, Context ctx) {
        try {
            return TimeUtil.generalParseDay(TimeUtil.getDateFormatInSec()
                    .parse(date), ctx);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将时间转换为昨天，今天几天前
     *
     * @param date
     * @return
     */
    public static int parseDay(Date date) {
        String s = TimeUtil.getTimeStrInDay(date);
        return parseDay(s);
    }

    public static int parseDay(String dateStr) {
        // 今天
        Calendar day = Calendar.getInstance();
        if (dateStr.equals(TimeUtil.getTimeStrInDay(day.getTime()))) {
            return DAY_TODAY;
        }

        // 昨天
        day.add(Calendar.DATE, -1);
        if (dateStr.equals(TimeUtil.getTimeStrInDay(day.getTime()))) {
            return DAY_YESTODAY;
        }
        // 前天
        day.add(Calendar.DATE, -1);
        if (dateStr.equals(TimeUtil.getTimeStrInDay(day.getTime()))) {
            return DAY_BEFORE_YESTODAY;
        }

        return DAY_UNKNOW;

    }

    /**
     * 从秒数取得小时数, 分钟数,秒数
     *
     * @param seconds
     * @return
     */
    public static int[] toHMSfromSec(int seconds) {
        int h = seconds / 3600;
        int m = seconds % 3600 / 60;
        int s = seconds - h * 3600 - m * 60;
        return new int[]{h, m, s};
    }

    /**
     * 将秒转换为HH:MM
     *
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":"
                        + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String secToTimeString(int time, Context mContext) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "0秒";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute)
                        + mContext.getString(R.string.date_minute)
                        + unitFormat(second)
                        + mContext.getString(R.string.date_second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99" + mContext.getString(R.string.date_hour) + "59"
                            + mContext.getString(R.string.date_minute) + "59"
                            + mContext.getString(R.string.date_second);
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour)
                        + mContext.getString(R.string.date_hour)
                        + unitFormat(minute)
                        + mContext.getString(R.string.date_minute)
                        + unitFormat(second)
                        + mContext.getString(R.string.date_second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static void main(String[] args) {
        System.out.println(TimeUtil.parseStrInMillis("2013092120180"));
    }

    /**
     * 根据YYYYMMDD出生年月获取年龄
     *
     * @param birthday
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static int getAge(String birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar birthdayCalendar = Calendar.getInstance();

        try {
            birthdayCalendar.setTime(dateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int year = birthdayCalendar.get(Calendar.YEAR);
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
        return nowYear - year;
    }

    /**
     * 将yyyyMMdd格式转换成yyyy-MM-dd HH:mm:ss格式
     *
     * @param cdate
     * @return
     */
    public static String formatCouldDayToLocal(String cdate) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC.get().parse(cdate);
            return DF_IN_SEC2.get().format(couldDate);
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 将yyyyMMddHHMMSSSSS格式转换成yyyy-MM-dd HH:mm:ss格式
     *
     * @param cdate
     * @return
     */
    public static String formatCouldMilsToLocal(String cdate) {
        Date couldDate;
        try {
            couldDate = DF_IN_MILLIS.get().parse(cdate);
            return DF_IN_SEC2.get().format(couldDate);
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 将本地时间格式转换为云端时间格式
     *
     * @param cdate
     * @return
     */
    public static String formatLocalToCould(String cdate) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC2.get().parse(cdate);
            return DF_IN_SEC.get().format(couldDate) + "000";
        } catch (ParseException e) {
            LogUtil.e("formatLocalToCould", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 將YYYY-MM-DD HH:MM:SS 轉換為MM-DD
     *
     * @param cdate
     * @return
     */
    public static String parseMMDDStrInDay(String cdate) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC2.get().parse(cdate);
            return DF_IN_DAY3.get().format(couldDate);
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 將YYYY-MM-DD HH:MM:SS 轉換為MM-DD
     *
     * @param cdate
     * @return
     */
    public static String parseDDStrInDay(String cdate) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC2.get().parse(cdate);
            return DF_IN_DAY4.get().format(couldDate);
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 將YYYY-MM-DD HH:MM:SS 轉換為HH:MM
     *
     * @param cdate
     * @return
     */
    public static String parseHHMMStrInDay(String cdate) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC2.get().parse(cdate);
            return DF_IN_HOUR_MIN.get().format(couldDate);
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 將YYYY-MM-DD HH:MM:SS 轉換為DD/MM 星期几 上午HH点
     *
     * @param cdate
     * @return
     */
    public static String parseDDMMStrInTime(String cdate, Context ctx) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC2.get().parse(cdate);
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append(DF_IN_DAY_DDMM.get().format(couldDate) + " ");
            Calendar c = Calendar.getInstance();
            c.setTime(couldDate);
            String Week = "";
            switch (c.get(Calendar.DAY_OF_WEEK) - 1) {
                case 1:
                    Week = ctx.getString(
                            R.string.date_sunday);
                    break;
                case 2:
                    Week = ctx.getString(
                            R.string.date_monday);
                    break;
                case 3:
                    Week = ctx.getString(
                            R.string.date_tuesday);
                    break;
                case 4:
                    Week = ctx.getString(
                            R.string.date_wednesday);
                    break;
                case 5:
                    Week = ctx.getString(
                            R.string.date_thursday);
                    break;
                case 6:
                    Week = ctx.getString(
                            R.string.date_friday);
                    break;
                case 7:
                    Week = ctx.getString(
                            R.string.date_saturday);
                    break;
                default:
                    break;
            }
            dateBuffer.append(Week + " ");
            String amOpm = "";
            if (c.get(Calendar.HOUR_OF_DAY) >= 0
                    && c.get(Calendar.HOUR_OF_DAY) < 6) {
                // 凌晨
                amOpm = ctx
                        .getString(
                                R.string.date_early_morning_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        c.getTime()));
            } else if (c.get(Calendar.HOUR_OF_DAY) >= 6
                    && c.get(Calendar.HOUR_OF_DAY) < 12) {
                // 上午
                amOpm = ctx
                        .getString(
                                R.string.date_am_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        c.getTime()));
            } else if (c.get(Calendar.HOUR_OF_DAY) >= 12
                    && c.get(Calendar.HOUR_OF_DAY) < 18) {
                // 下午
                amOpm = ctx
                        .getString(
                                R.string.date_pm_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        c.getTime()));
            } else {
                // 晚上
                amOpm = ctx
                        .getString(
                                R.string.date_evening_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        c.getTime()));
            }
            dateBuffer.append(amOpm);
            return dateBuffer.toString();
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    public static String parseStrToYMDHMSWeek(String cdate, Context ctx) {
        Date couldDate;
        try {
            couldDate = DF_IN_SEC2.get().parse(cdate);
            StringBuffer dateBuffer = new StringBuffer();
            dateBuffer.append(DF_IN_SEC2.get().format(couldDate) + "\n");
            Calendar c = Calendar.getInstance();
            c.setTime(couldDate);
            String Week = "";
            switch (c.get(Calendar.DAY_OF_WEEK) - 1) {
                case 1:
                    Week = ctx.getString(
                            R.string.date_sunday);
                    break;
                case 2:
                    Week = ctx.getString(
                            R.string.date_monday);
                    break;
                case 3:
                    Week = ctx.getString(
                            R.string.date_tuesday);
                    break;
                case 4:
                    Week = ctx.getString(
                            R.string.date_wednesday);
                    break;
                case 5:
                    Week = ctx.getString(
                            R.string.date_thursday);
                    break;
                case 6:
                    Week = ctx.getString(
                            R.string.date_friday);
                    break;
                case 7:
                    Week = ctx.getString(
                            R.string.date_saturday);
                    break;
                default:
                    break;
            }
            dateBuffer.append(Week + " ");
            return dateBuffer.toString();
        } catch (ParseException e) {
            LogUtil.e("formatCouldDayToLocal", Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 将时间转换为类似微信的时间， 当天显示具体MM:SS 昨天 显示昨天 前天到第10天显示 1天前至10天前，10天以前显示MM-DD
     *
     * @param date
     * @return
     */
    public static String generalParseDay(Date date, Context ctx) {
        String dateStr = TimeUtil.getTimeStrInDay(date);
        Calendar day = Calendar.getInstance();
        if (dateStr.equals(TimeUtil.getTimeStrInDay(day.getTime()))) {
            day.setTime(date);
            String amOpm = "";
            if (day.get(Calendar.HOUR_OF_DAY) >= 0
                    && day.get(Calendar.HOUR_OF_DAY) < 6) {
                // 凌晨
                amOpm = ctx
                        .getString(
                                R.string.date_early_morning_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        day.getTime()));
            } else if (day.get(Calendar.HOUR_OF_DAY) >= 6
                    && day.get(Calendar.HOUR_OF_DAY) < 11) {
                // 上午
                amOpm = ctx
                        .getString(
                                R.string.date_am_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        day.getTime()));
            } else if (day.get(Calendar.HOUR_OF_DAY) >= 11
                    && day.get(Calendar.HOUR_OF_DAY) < 13) {
                // 中午
                amOpm = ctx
                        .getString(
                                R.string.date_noon_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        day.getTime()));
            } else if (day.get(Calendar.HOUR_OF_DAY) >= 13
                    && day.get(Calendar.HOUR_OF_DAY) < 18) {
                // 下午
                amOpm = ctx
                        .getString(
                                R.string.date_pm_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        day.getTime()));
            } else {
                // 晚上
                amOpm = ctx
                        .getString(
                                R.string.date_evening_hhmm,
                                TimeUtil.getDateFormatInHourMin().format(
                                        day.getTime()));
            }
            // 今天
            return amOpm;
        }
        // 昨天
        day.add(Calendar.DATE, -1);
        if (dateStr.equals(TimeUtil.getTimeStrInDay(day.getTime()))) {
            return ctx.getString(R.string.date_yesterday);
        }
//		day.add(Calendar.DATE, -1);
        long endTime = day.getTimeInMillis();
        day.add(Calendar.DATE, -6);
        long startTime = day.getTimeInMillis();
        // 7天内
        if (date.getTime() >= startTime && date.getTime() <= endTime) {
            long diff = date.getTime()
                    - Calendar.getInstance().getTimeInMillis();
            long days = diff / (1000 * 60 * 60 * 24);
            return ctx
                    .getString(R.string.date_before_n_days, Math.abs(days));
        }
        // 显示MM-dd
        return getTimeStrInDay3(date);
    }

    public static String getDayBeginTimeStr(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return DF_IN_SEC2.get().format(cal.getTime());
    }

    public static String getDayEndTimeStr(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return DF_IN_SEC2.get().format(cal.getTime());
    }

    public static String getDateHourMinute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd HH:mm",
                Locale.getDefault());
        return format.format(date);
    }

    public static boolean isTheSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH));
    }

    public static boolean isTheSameMonth(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
    }

    public static boolean isTheSameYear(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    public static String getNextDayString(String dayString) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(getDateFormatInDay().parse(dayString));
        } catch (ParseException e) {
            LogUtil.e("getNextDayString", Log.getStackTraceString(e));
        }
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return getDateFormatInDay().format(cal.getTime());
    }

    public static String getPreviousDayString(String dayString) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(getDateFormatInDay().parse(dayString));
        } catch (ParseException e) {
            LogUtil.e("getPreviousDayString", Log.getStackTraceString(e));
        }
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return getDateFormatInDay().format(cal.getTime());
    }

    /**
     * 将字符串转为date
     */
    public static long getStringToDate(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 将字符串转为date
     */
    public static long getStringToDate2(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /* 时间戳转换成字符串年-月-日 */
    public static String getDateToString(long time) {
        Date d;
        if (time <= 0) {
            d = new Date();
        } else {
            d = new Date(time);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /* 时间戳转换成字符串年-月-日 时：分：秒 */
    public static String getDateToStringTime(long time) {
        Date d;
        if (time == 0) {
            d = new Date();
        } else {
            d = new Date(time);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /**
     * 时间戳转换成字符串年-月-日
     */
    public static String getDateToYearMonth(long time) {
        Date d;
        if (time <= 0) {
            d = new Date();
        } else {
            d = new Date(time);
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d);
        c2.setTime(new Date());
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH)) {
            return "今天   星期" + changeEtoC(c1.get(Calendar.DAY_OF_WEEK) - 1);
        } else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH) - 1) {
            return "昨天   星期" + changeEtoC(c1.get(Calendar.DAY_OF_WEEK) - 1);
        } else if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            SimpleDateFormat sf = new SimpleDateFormat("M月d日");
            return sf.format(d) + "   星期"
                    + changeEtoC(c1.get(Calendar.DAY_OF_WEEK) - 1);
        } else {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy年M月d日");
            return sf.format(d) + "   星期"
                    + changeEtoC(c1.get(Calendar.DAY_OF_WEEK) - 1);
        }
    }

    /**
     * 时间戳转换成字符串年-月-日 时分
     */
    public static String getDateToYearMonthHour(long time) {
        Date d;
        if (time <= 0) {
            d = new Date();
        } else {
            d = new Date(time);
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d);
        c2.setTime(new Date());
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH)) {
            if (c1.get(Calendar.HOUR_OF_DAY) < 6
                    && c1.get(Calendar.HOUR_OF_DAY) >= 0) {
                SimpleDateFormat sf = new SimpleDateFormat("凌晨 h:mm");
                return sf.format(d);
            } else if (c1.get(Calendar.HOUR_OF_DAY) < 12
                    && c1.get(Calendar.HOUR_OF_DAY) >= 6) {
                SimpleDateFormat sf = new SimpleDateFormat("上午 h:mm");
                return sf.format(d);
            } else if (c1.get(Calendar.HOUR_OF_DAY) < 18
                    && c1.get(Calendar.HOUR_OF_DAY) >= 12) {
                SimpleDateFormat sf = new SimpleDateFormat("下午 h:mm");
                return sf.format(d);
            } else {
                SimpleDateFormat sf = new SimpleDateFormat("晚上 h:mm");
                return sf.format(d);
            }
        } else {
            if (c1.get(Calendar.HOUR_OF_DAY) < 6
                    && c1.get(Calendar.HOUR_OF_DAY) >= 0) {
                SimpleDateFormat sf = new SimpleDateFormat("M月d日 凌晨 h:mm");
                return sf.format(d);
            } else if (c1.get(Calendar.HOUR_OF_DAY) < 12
                    && c1.get(Calendar.HOUR_OF_DAY) >= 6) {
                SimpleDateFormat sf = new SimpleDateFormat("M月d日 上午 h:mm");
                return sf.format(d);
            } else if (c1.get(Calendar.HOUR_OF_DAY) < 18
                    && c1.get(Calendar.HOUR_OF_DAY) >= 12) {
                SimpleDateFormat sf = new SimpleDateFormat("M月d日 下午 h:mm");
                return sf.format(d);
            } else {
                SimpleDateFormat sf = new SimpleDateFormat("M月d日 晚上 h:mm");
                return sf.format(d);
            }
        }
    }

    public static String changeEtoC(int num) {
        String s = "";
        switch (num) {
            case 0:
                s = "日";
                break;
            case 1:
                s = "一";
                break;
            case 2:
                s = "二";
                break;
            case 3:
                s = "三";
                break;
            case 4:
                s = "四";
                break;
            case 5:
                s = "五";
                break;
            case 6:
                s = "六";
                break;
            case 7:
                s = "日";
                break;
        }
        return s;
    }

    /**
     * 秒转换mm:ss
     *
     * @return
     */
    public static String getTimeToMS(Context context, int time) {
        String mTimerFormat = context.getString(R.string.timer_format);
        return String.format(mTimerFormat, time / 60, time % 60);
    }


    public static Long changeStringToData(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String changeTimeToMMss(int time) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss",
                Locale.getDefault());
        return format.format(time);
    }



}
