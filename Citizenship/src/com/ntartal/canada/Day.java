package com.ntartal.canada;
import java.util.Calendar;
import java.util.Date;


public class Day {
    //The day that you left Canada is not a resident day
    private boolean residentDays = false;
    private Date date;
    private boolean inCanada = false;
    public static Date landingDate;

    public boolean isResidentDays() {
        return residentDays;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date.getTime() >= landingDate.getTime()) {
            this.residentDays = true;
        } 
        this.date = date;
    }

    public boolean isInCanada() {
        return inCanada;
    }

    public boolean isSameDay(Date comparedDay) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(this.getDate());
        c2.setTime(comparedDay);
        
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
                if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void setInCanada() {
        this.inCanada = true;
    }

}
