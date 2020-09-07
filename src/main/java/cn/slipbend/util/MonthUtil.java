package cn.slipbend.util;

import java.util.Calendar;

public class MonthUtil {

    /**
     * 获取指定月的前一月（年）或后一月（年）
     *
     * 使用示例：
     * getLastMonth("2011-06",0,-1,0);//2011-05
     * getLastMonth("2011-06",0,-6,0);//2010-12
     * getLastMonth("2011-06",-1,0,0);//2010-06
     *
     * @param dateStr
     * @param addYear
     * @param addMonth
     * @param addDate
     * @return 输入的时期格式为yyyy-MM，输出的日期格式为yyyy-MM
     * @throws Exception
     */
    public static String getLastMonth(String dateStr,int addYear, int addMonth, int addDate) throws Exception {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM");
            java.util.Date sourceDate = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.YEAR,addYear);
            cal.add(Calendar.MONTH, addMonth);
            cal.add(Calendar.DATE, addDate);

            java.text.SimpleDateFormat returnSdf = new java.text.SimpleDateFormat("yyyy-MM");
            String dateTmp = returnSdf.format(cal.getTime());
            java.util.Date returnDate = returnSdf.parse(dateTmp);
            return dateTmp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }


    /**
     * 获取某年某月有多少天
     * @param date 年月 格式示例：2020-05
     * @return
     */
    public static int getDayOfMonth(String date){
        Integer year = Integer.parseInt(date.substring(0,4));
        Integer month = Integer.parseInt(date.substring(5,7));
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        return c.get(Calendar.DAY_OF_MONTH);
    }

}
