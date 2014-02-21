package com.ntartal.canada;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Entry implements Comparable<Entry>{
    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    public Date getLeave() {
        return leave;
    }

    public void setLeave(Date leave) {
        this.leave = leave;
    }

    private Date entry;
    private Date leave;
    
    public Entry(Date entry, Date leave) {
        this.entry = entry;
        this.leave = leave;
    }
    
    public Entry(Date entry) {
        this.entry = entry;
        this.leave = new Date();
    }
    
    public Entry(String entry, String leave) {
        this(DateParser.parseDate(entry), DateParser.parseDate(leave));
    }
    
    public Entry(String entry) {
        this(DateParser.parseDate(entry));
    }
    
    public int getDuration() {
        if (leave != null) {
            Calendar start = new GregorianCalendar();
            Calendar end = new GregorianCalendar();
            start.setTime(entry);
            end.setTime(leave);
            Date startDate = start.getTime();
            Date endDate = end.getTime();
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long diffTime = endTime - startTime;
            long diffDays = diffTime / (1000 * 60 * 60 * 24);
            return (int) diffDays;
        } 
        this.leave = new Date();
        int dif = this.getDuration();
        leave = null;
        return dif;
    }

    @Override
    public int compareTo(Entry other) {
        int entryComp = this.entry.compareTo(other.entry);
        if (entryComp == 0) {
            if (this.leave == null) {
                //If there is no exit, means that it's the last item in the chronological list
                return 1;
            } else if (other.leave == null) {
                //Compared item is the biggest.
                return -1;
            } else {
                //Compares the dates like landing date leave entry is the same date. (In Canada applicants)
                return this.leave.compareTo(other.leave); 
            }
        }
        return entryComp;
    }
    
    public String toString() {
        return this.entry + " - " + this.leave;
    }
    
    public boolean containsDate(Date date) {
        if (date.getTime() >= entry.getTime()) {
            if (date.getTime() <= leave.getTime()) {
                return true;
            }
        } 
        return false;
    }
    
    public boolean containsDate(String date) {
        return this.containsDate(DateParser.parseDate(date));
    }
}
