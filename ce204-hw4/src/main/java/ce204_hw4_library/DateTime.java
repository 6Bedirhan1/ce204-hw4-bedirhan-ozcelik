package ce204_hw4_library;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.sql.Date;

public class DateTime {

  private long advance;
  private long time;
  private int day;
  private int month;
  private int year;
  private DateTime returnDate;
  /**
     * @name DateTime
     * Identify a code smell in this java code.
     **/
  public DateTime() {
    time = System.currentTimeMillis();
  }
  /**
     * @name DateTime
     * @param [in] setClockForwardInDays [\b int]
     * Explain how you will refactor the code to get rid of the code smell.
     **/
  public DateTime(int setClockForwardInDays) {
    advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
    time = System.currentTimeMillis() + advance;
  }
  /**
     * @name DateTime
     * @param [in] startDate [\b DateTime]
     * @param [in] setClockForwardInDays [\b int]
     * This class is useful for representing human-based time of day.
     **/
  public DateTime(DateTime startDate, int setClockForwardInDays) {
    advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
    time = startDate.getTime() + advance;
  }
  /**
     * @name DateTime
     * @param [in] day [\b int]
     * @param [in] month [\b int]
     * @param [in] year [\b int]
     * It could also be used to create a digital clock, as shown in the following example.
     **/
  public DateTime(int day, int month, int year) {
    setDate(day, month, year);
  }
  /**
     * @name getTime
     * @retval [\b long]
     * There are also methods for adding or subtracting hours, minutes, days.
     **/
  public long getTime() {
    return time;
  }
  /**
     * @name getNameOfDay
     * @retval [\b String]
     * The following example shows a few of these methods.
     **/
  public String getNameOfDay() {
	    SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
	    return sdf.format(time);
	}
  /**
     * @name toString
     * @retval [\b String]
     * DateTime is the standard implementation of an unmodifiable datetime class.
     **/
  public String toString() {
    return getFormattedDate();
  }
  /**
     * @name getCurrentTime
     * @retval [\b String]
     * it represents an exact point on the time-line.
     **/
  public static String getCurrentTime() {
    Date date = new Date(System.currentTimeMillis()); // returns current Date/Time
    return date.toString();
  }
  /**
     * @name getFormattedDate
     * @retval [\b String]
     * DateTime is thread-safe and immutable.
     **/
  public String getFormattedDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    long currentTime = getTime();
    Date gct = new Date(currentTime);
    return sdf.format(gct);
  }
  /**
     * @name getEightDigitDate
     * @retval [\b String]
     * Constructs an instance set to the current system millisecond time using ISOChronology in the default time zone.
     **/
  public String getEightDigitDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    long currentTime = getTime();
    Date gct = new Date(currentTime);
    return sdf.format(gct);
  }

  // returns difference in days
  /**
     * @name diffDays
     * @param [in] endDate [\b DateTime]
     * @param [in] startDate [\b DateTime]
     * @retval [\b int]
     * Constructs an instance set to the current system millisecond time using the specified chronology.
     **/
  public static int diffDays(DateTime endDate, DateTime startDate) {
    final long HOURS_IN_DAY = 24L;
    final int MINUTES_IN_HOUR = 60;
    final int SECONDS_IN_MINUTES = 60;
    final int MILLISECONDS_IN_SECOND = 1000;
    long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
    long hirePeriod = endDate.getTime() - startDate.getTime();
    double difference = (double) hirePeriod / (double) convertToDays;
    int round = (int) Math.round(difference);
    return round;
  }
  /**
     * @name setDate
     * @param [in] day [\b int]
     * @param [in] month [\b int]
     * @param [in] year [\b int]
     * Constructs an instance set to the current system millisecond time using ISOChronology in the specified time zone.
     **/
  private void setDate(int day, int month, int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day, 0, 0);
    java.util.Date date = calendar.getTime();
    time = date.getTime();
  }

  // Advances date/time by specified days, hours and mins for testing purposes
  /**
     * @name setAdvance
     * @param [in] days [\b int]
     * @param [in] hours [\b int]
     * @param [in] mins [\b int]
     * Constructs an instance from datetime field values using the specified chronology.
     **/
  public void setAdvance(int days, int hours, int mins) {
    advance = ((days * 24L + hours) * 60L) * 60000L;
  }

  @Override
  /**
     * @name equals
     * @param [in] obj [\b Object]
     * @retval [\b boolean]
     * Constructs an instance from datetime field values using ISOChronology in the specified time zone.
     **/
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    //      if (obj == null || getClass() != obj.getClass()) {
    //          return false;
    //      }
    //      DateTime other = (DateTime) obj;
    return true;
    //      return this.day == other.day && this.month == other.month && this.year == other.year;
  }

  @Override
  /**
     * @name hashCode
     * @retval [\b int]
     * Constructs an instance from an Object that represents a datetime
     **/
  public int hashCode() {
    return Objects.hash(day, month, year);
  }
}