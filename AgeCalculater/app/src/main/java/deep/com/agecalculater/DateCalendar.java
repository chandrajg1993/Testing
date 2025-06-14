package deep.com.agecalculater;
import java.util.Calendar;
public final class DateCalendar {
    private final int year;
    private final int month;
    private final int day;
    private final int totalDay;
    public DateCalendar(int year, int month, int day, int totalDay) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalDay = totalDay;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getTotalDay() {
        return totalDay;
    }
    public static int MonthsToDays(int tMonth, int tYear) {
        if (tMonth == 1 || tMonth == 3 || tMonth == 5 || tMonth == 7
                || tMonth == 8 || tMonth == 10 || tMonth == 12) {
            return 31;
        } else if (tMonth == 2) {
            if (tYear % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }
    public static DateCalendar calculateAge(Calendar startDate, Calendar endDate) {
        Calendar mycal1 = (Calendar) startDate.clone();
        Calendar mycal2 = (Calendar) endDate.clone();

        long d1 = mycal1.getTimeInMillis();
        long d2 = mycal2.getTimeInMillis();

        if (d1 > d2) {
            mycal1 = (Calendar) endDate.clone();
            mycal2 = (Calendar) startDate.clone();
        }

        int mDay = mycal1.get(Calendar.DAY_OF_MONTH);
        int mMonth = mycal1.get(Calendar.MONTH);
        int mYear = mycal1.get(Calendar.YEAR);

        int tDay = mycal2.get(Calendar.DAY_OF_MONTH);
        int tMonth = mycal2.get(Calendar.MONTH);
        int tYear = mycal2.get(Calendar.YEAR);

        int totalDays=gregorianDays(tYear,tMonth,tDay) - gregorianDays(mYear,mMonth,mDay);
        int mYearDiff = tYear - mYear;
        int mMonDiff = tMonth - mMonth;

        if (mMonDiff < 0) {
            mYearDiff = mYearDiff - 1;
            mMonDiff = mMonDiff + 12;
        }

        int mDayDiff = tDay - mDay;
        if (mDayDiff < 0) {
            if (mMonDiff > 0) {
                mMonDiff = mMonDiff - 1;
                mDayDiff = mDayDiff + MonthsToDays(tMonth - 1, tYear);
            } else {
                mYearDiff = mYearDiff - 1;
                mMonDiff = 11;
                mDayDiff = mDayDiff + MonthsToDays(tMonth - 1, tYear);;
            }

        }
        return new DateCalendar(mYearDiff, mMonDiff, mDayDiff, totalDays);
    }
    private static int gregorianDays(int year, int month, int day) {
        month = (month + 9) % 12;
        year = year - month / 10;
        return 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 1);
    }
}
