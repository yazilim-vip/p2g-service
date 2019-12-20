package vip.yazilim.p2g.web.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author mustafaarifsisman - 26.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
public class TimeHelper {

//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public static Long getCurrentTimeMs() {
        return new Date().getTime();
    }

    public static String getCurrentTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime()).toString();
    }

    public static Date getCurrentDate() {
        return new Date();
    }

}
