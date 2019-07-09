package com.wenzheng.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //格式化
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     *
     * 将Date转换成String
     * @param date
     * @return
     */
    public String parseDateToString(Date date){
        String time = format.format(date);
        return time;
    }

    /**
     * 将String转换成Date
     * @param dateStr
     * @return
     */
    public Date parseStringToDate(String dateStr){
        Date date = null;

        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
