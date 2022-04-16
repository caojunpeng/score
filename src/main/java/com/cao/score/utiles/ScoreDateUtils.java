package com.cao.score.utiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreDateUtils {

    private static Logger logger = LoggerFactory.getLogger(ScoreFileUtil.class);

    public static final String format_date = "yyyy-MM-dd HH:mm:ss";
    public static final String format_day = "yyyy-MM-dd";
    public static final String format_dayStr = "yyyyMMdd";

    /**
     * 字符串转日期
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseStrToDate(String strDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date strtodate = null;
        try {
            strtodate = formatter.parse(strDate);
        } catch (Exception e) {
            logger.error("日期获取异常" + e.getMessage(), e);
        }
        return strtodate;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        String dateString = "";
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            dateString = formatter.format(date);
        }
        return dateString;
    }


}
