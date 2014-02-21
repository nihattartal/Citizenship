package com.ntartal.canada;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateParser{

    public static String dateFormat = "dd-MM-yyyy";
    
    private DateParser() {
    }
    
    public static Date parseDate(String dateStr) {
        if (dateStr != null && !"".equals(dateStr)) {
            Date date = null;
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(dateFormat); //yyy-MM-dd
                date = formatter.parse(dateStr);
                return date;
            } catch(Exception e) {
                System.err.println("Parsing error: " + date + " with " + dateFormat + " format");
            }
        }
        return null;
    }
}
