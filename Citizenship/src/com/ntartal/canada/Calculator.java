package com.ntartal.canada;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;


public class Calculator {
    public final static int DAYS_IN_A_YEAR = 365;
    private final static int RESIDENCY_PERIOD = DAYS_IN_A_YEAR * 4;
    private Day[] days;
    
    private TreeSet<Entry> entries;
    
    public Calculator(TreeSet<Entry> entries, Date landingDate) {
        this.entries = new TreeSet<Entry>(entries);
        Day.landingDate = landingDate;
    }
    
    public Calculator(TreeSet<Entry> entries, String landingDate) {
        this(entries, DateParser.parseDate(landingDate));
    }
    
    public Timetable calculate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        days = new Day[RESIDENCY_PERIOD];
        for (int i = 0; i < RESIDENCY_PERIOD; i++) {
            Day d = new Day();
            c.add(Calendar.DATE, -1);
            //LEAP DAY IS NOT COUNTED
            if (c.get(Calendar.DAY_OF_MONTH) == 29 && c.get(Calendar.MONDAY) == Calendar.FEBRUARY) {
                c.add(Calendar.DATE, -1);
            }
            d.setDate(c.getTime());
            days[RESIDENCY_PERIOD - i - 1] = d;
        }
        //Time-series are ready
        for (Entry e : entries) {
            int duration = e.getDuration();
            int cursor = getCursorOfTheDate(e.getEntry());
            
            int l = 0;
            for (int i = 0; i < duration; i++) {
                if (cursor != -1) {
                    days[cursor + l++].setInCanada();
                } else {
                    /* * * * * * * * 
                     * NOT TESTED (NEEDS TO BE TESTED)
                     * * * * * * * */
                    Calendar tempCal = Calendar.getInstance(); 
                    tempCal.setTime(e.getEntry()); 
                    tempCal.add(Calendar.DATE, 1);
                    cursor = getCursorOfTheDate(tempCal.getTime());
                }
            }
        }
        Timetable table = new Timetable();
        for(Day d : days) {
            if (d.isInCanada()) {
                if (d.isResidentDays()) {
                    table.residentDays++;
                } else {
                    table.nonResidentDays++;
                }
            }
        }
        return table;
    }
    
    private int getCursorOfTheDate(Date d) {
        for (int i = 0; i < RESIDENCY_PERIOD; i++) {
           if (days[i].isSameDay(d)) {
               return i;
           }
        }
        return -1;
    }
    
    public void printToFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("c:/temp/file.txt"));
            for(Day d : days) {
                if (d.isInCanada()) {
                    out.write(d.getDate() + (d.isResidentDays() ? " Resident" : " Non-Resident") + "\r\n");
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }
    
}
