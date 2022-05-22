/*
 * The Time class, a helper class used to help better organize the time
 */
//Time represents a time interval
//We pretend every month is 30 days here
public class Time {
	private int month;
	private int date;
	private int hour; 
	private int minute; 
	//in terms of 24 hour, ex: 2:15 PM is represented with hour=14, minute=15
	//midnight is represented with hour=0, minute=0
	//month, date, hour, and minute make up the start time of the time interval
	private int duration; //duration of the time interval in terms of minute
	//ex: 125 minute duration is represented with duration=125
	private int endMonth;
	private int endDate;
	private int endHour;
	private int endMinute;
	
	public Time(int month, int date, int hour, int minute, int duration) {
		if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month input is invalid");
        }
		if (date < 1 || date > 30) {
            throw new IllegalArgumentException("Date input is invalid");
        }
		if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour input is invalid");
        }
		if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute input is invalid");
        }
		if (duration < 0) {
            throw new IllegalArgumentException("Duration input is invalid");
        }
		this.month = month;
	    this.date = date;
	    this.hour = hour;
	    this.minute = minute;
	    this.duration = duration;
	    this.endMonth = 0;
	    this.endDate = 0;
	    this.endHour = 0;
	    this.endMinute = minute + duration;
	    if (this.endMinute > 59) {
	    	this.endHour = this.endMinute/60;
	    	this.endMinute = this.endMinute%60;
	    }
	    this.endHour = this.endHour + hour;
	    if (this.endHour > 23) {
	    	this.endDate = this.endHour/24;
	    	this.endHour = this.endHour%24;
	    }
	    this.endDate = this.endDate + date;
	    if (this.endDate > 30) {
	    	this.endMonth = this.endDate/30;
	    	this.endDate = this.endDate%30;
	    }
	    this.endMonth = this.endMonth + month;
	    if (this.endMonth > 12) {
	    	this.endMonth = this.endMonth%12;
	    }
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDate() {
		return date;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getEndMonth() {
		return endMonth;
	}
	
	public int getEndDate() {
		return endDate;
	}
	
	public int getEndHour() {
		return endHour;
	}
	
	public int getEndMinute() {
		return endMinute;
	}
	
	// if this start time is earlier than t's end time, return -1
	// if they're equal, return 0
	// otherwise, return 1
	public int compareStartEnd(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
            return 0;
        }
		if (this.month == t.getEndMonth() && this.date == t.getEndDate() && this.hour == t.getEndHour() && this.minute == t.getEndMinute()){
			return 0;
		}
		if (this.month < t.getEndMonth()) {
			return -1;
		} else if (this.month == t.getEndMonth() && this.date < t.getEndDate()){
			return -1;
		} else if (this.month == t.getEndMonth() && this.date == t.getEndDate() && this.hour < t.getEndHour()){
			return -1;
		} else if (this.month == t.getEndMonth() && this.date == t.getEndDate() && this.hour == t.getEndHour() && this.minute < t.getEndMinute()){
			return -1;
		} else {
			return 1;
		}
	}
	
	//returns true if this time interval and time t interval overlap with each other, otherwise returns false
	public boolean overlaps(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
            return false;
        }
		if (this.month == t.getMonth() && this.date == t.getDate() && this.hour == t.getHour() && this.minute == t.getMinute()){
			return true;
		}
		if (this.compareStartEnd(t) < 0 && t.compareStartEnd(this) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//checks if this time and the input time are equal
	public boolean equals(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
            return false;
        }
		if (this.month == t.getMonth() && this.date == t.getDate() && this.hour == t.getHour() && 
				this.minute == t.getMinute()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void printTime() {
		System.out.print(this.month+"/"+this.date+" ");
		if (this.hour < 10) {
			System.out.print("0");
		}
		System.out.print(this.hour+":");
		if (this.minute < 10) {
			System.out.print("0");
		}
		System.out.print(this.minute+" to ");
		System.out.print(this.endMonth+"/"+this.endDate+" ");
		if (this.endHour < 10) {
			System.out.print("0");
		}
		System.out.print(this.endHour+":");
		if (this.endMinute < 10) {
			System.out.print("0");
		}
		System.out.println(this.endMinute);
	}
}