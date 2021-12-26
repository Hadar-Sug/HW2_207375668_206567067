import java.util.Objects;

public class Clock {
    private int hours;
    private int minutes;

    /**
     * constructor for Clock
     * @param hours hours
     * @param minutes minutes
     */
    public Clock(int hours, int minutes) {
        setHours(hours);
        setMinutes(minutes);
    }

    /**
     * getter for hours
     * @return hours
     */
    public int getHours(){
        return hours;
    }

    /**
     * setter for hours
     * @param hours hour
     */
    public void setHours(int hours) {
        this.hours = hours;
        if (!validHour(hours)) {
            this.hours = 0;
        }

    }

    /**
     * getter for minutes
     * @return minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * setter for minutes
     * @param minutes minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
        if (!validMinute(minutes)) {
            this.minutes = 0;
        }
    }

    /**
     * gives the hour formatted as such : "HH:MM"
     * @return the time
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * checks equality of two clocks
     * equals if and only if H=H & M=M
     * @param obj the object were checking equality with
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // if they're the same object were done
        if (!(obj instanceof Clock)) return false; // if the object isn't a clock, we're done
        Clock clock = (Clock) obj; // it's a clock! hooray!
        return hours == clock.hours && minutes == clock.minutes; // let's compare
    }

    /**
     * different hashcode if clocks are different
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return ((60*hours) + minutes);
    }

    /**
     * check validity of hour value
     * @param hour value were checking
     * @return true if valid, false if not
     */
    private boolean validHour(int hour){
        return  !(hour > 23 || hour < 0 );
    } // gotta check edge cases, rounding etc 23 or 24?

    /**
     * check validity of minute value
     * @param minute value were checking
     * @return true if valid, false if not
     */
    private boolean validMinute(int minute){
        return  !(minute>59 || minute < 0 );
    }
}
