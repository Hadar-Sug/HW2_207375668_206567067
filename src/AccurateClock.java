import java.util.Objects;

public class AccurateClock extends Clock {
    private int seconds;

    /**
     * constructor for an AccurateClock
     * @param seconds seconds
     */
    public AccurateClock(int hours, int minutes , int seconds) {
        super(hours,minutes);
        setSeconds(seconds);
    }

    /**
     * getter
     * @return seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * setter
     * @param seconds sets seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
        if (!(validSecond(seconds)))
            this.seconds = 0;
    }

    /**
     * gives string in the format of "HH:MM:SS"
     */
    @Override
    public String toString() {
        return super.toString() + String.format(":%02d", seconds);
    }

    /**
     * checks equality of hours, minutes and seconds
     * @param obj the object were checking equality with
     * @return true if equal, false if different
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // if they point to the same object were done
        if (!(obj instanceof AccurateClock)) return false; //by now we know its a similar clock, is it an AccurateClock
        AccurateClock accurateClock = (AccurateClock) obj; // it is an AccurateClock
        return super.equals(obj) && seconds == accurateClock.seconds; // compare seconds
    }

    /**
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return -(10000*super.getHours() + 100*super.getMinutes() + (seconds+1));//lower than zero
    }

    /**
     * checks validity for seconds value
     * @param second the value were checking
     * @return true if valid, false if not
     */
    private boolean validSecond(int second){
        return  !(second>59 || second < 0 );
    }
}
