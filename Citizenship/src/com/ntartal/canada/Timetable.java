package com.ntartal.canada;

public class Timetable {
    public int nonResidentDays = 0;
    public int residentDays = 0;
    
    public int getTotalResudentDays() {
        return (int) (Math.min(365, Math.ceil(nonResidentDays/2)) + residentDays);
    }
    
}
