package com.ntartal.canada;
import java.util.TreeSet;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TreeSet<Entry> entries = new TreeSet<Entry>();
        
        /* Here enter all the Canada entries */
        entries.add(new Entry("24-08-2010","05-07-2011"));
        entries.add(new Entry("17-08-2011","14-12-2011"));
        entries.add(new Entry("09-01-2012","18-06-2012"));
        entries.add(new Entry("30-07-2012","01-12-2012"));
        entries.add(new Entry("01-12-2012","12-12-2012"));
        entries.add(new Entry("08-01-2013","23-05-2013"));
        entries.add(new Entry("23-05-2013","03-07-2013"));
        entries.add(new Entry("22-08-2013","14-09-2013"));
        entries.add(new Entry("16-09-2013","12-10-2013"));
        entries.add(new Entry("14-10-2013","20-11-2013"));
        entries.add(new Entry("24-11-2013"));
        
        //Here enter your landing date. (The day that you became permanent resident)
        Calculator c = new Calculator(entries, "23-05-2013");
        Timetable t = c.calculate();
       
        System.out.println("Completed residency days :" + t.getTotalResudentDays());
    }

}
