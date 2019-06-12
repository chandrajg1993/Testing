package deep.com.agecalculater;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText edittext1, edittext2;
    private ImageView firstDate, secondDate;
    Calendar Calendar;
    Button calculate, button2;
    TextView yearTv, Month, Day, Day3, Month3, totalYear, totalMonth, totalWeeks, totalDay, totalHours, totalMinutes, totalSecond;
    char f;
    DatePickerDialog.OnDateSetListener date;
    private int currentday;
    private int dateBirthday;
    private int currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar = Calendar.getInstance();
        firstDate = (ImageView) findViewById(R.id.image1);
        secondDate = (ImageView) findViewById(R.id.image2);
        edittext1 = (EditText) findViewById(R.id.editText1);
        edittext2 = (EditText) findViewById(R.id.editText2);
        calculate = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        yearTv = (TextView) findViewById(R.id.year);
        Month = (TextView) findViewById(R.id.month);
        Day = (TextView) findViewById(R.id.day);
        Month3 = (TextView) findViewById(R.id.month3);
        Day3 = (TextView) findViewById(R.id.day3);
        totalYear = (TextView) findViewById(R.id.total);
        totalMonth = (TextView) findViewById(R.id.total1);
        totalDay = (TextView) findViewById(R.id.total2);
        totalHours = (TextView) findViewById(R.id.total3);
        totalMinutes = (TextView) findViewById(R.id.total4);
        totalSecond = (TextView) findViewById(R.id.total5);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        edittext1.setText(sdf.format(Calendar.getTime()));

        firstDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = 'c';
                new DatePickerDialog(MainActivity.this, date, Calendar
                        .get(Calendar.YEAR), Calendar.get(Calendar.MONTH),
                        Calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        secondDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f = 'd';
                new DatePickerDialog(MainActivity.this, date, Calendar
                        .get(Calendar.YEAR), Calendar.get(Calendar.MONTH)
                        , Calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                Calendar.set(Calendar.YEAR, year);
                Calendar.set(Calendar.MONTH, monthOfYear);
                Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(f);
            }

        };

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String now = sdf.format(new Date());
                try {
                    Date date = sdf.parse(edittext1.getText().toString());
                    Date date1 = sdf.parse(edittext2.getText().toString());
                    long different = date.getTime() - date1.getTime();
                    Calendar cal;
                    cal = Calendar.getInstance();
                    int year1 = Calendar.get(Calendar.YEAR);
                    int leapYear = 0;
                    if (leapYear <= date1.getTime()) {
                        for (int x = year1; x <= 10; x++) {
                            System.out.print(leapYear + " ");
                            leapYear = leapYear + 4;
                        }
                        System.out.println(" ");
                    }

                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long daysInMilli = hoursInMilli * 24;
                    long monthsInMilli = (long) (daysInMilli * 30.436876);
                    long yearsInMilli = monthsInMilli * 12;

                    long elapsedYears = different / yearsInMilli;
                    different = different % yearsInMilli;


                    long elapsedMonths = different / monthsInMilli;
                    different = different % monthsInMilli;


                    long elapsedDays = different / daysInMilli;
                    different = different % daysInMilli;


                    long elapsedHours = different / hoursInMilli;
                    different = different % hoursInMilli;

                    long elapsedMinutes = different / minutesInMilli;
                    different = different % minutesInMilli;

                    long elapsedSeconds = different * secondsInMilli;


                    Log.d("vineetPandey", elapsedYears + "_" + elapsedMonths + "_" + elapsedDays);
                    yearTv.setText(String.valueOf(elapsedYears));
                    Month.setText(String.valueOf(elapsedMonths));
                    Day.setText(String.valueOf(elapsedDays));
                    totalYear.setText(String.valueOf(elapsedYears));
                    totalMonth.setText(String.valueOf(elapsedMonths));
                    totalDay.setText(String.valueOf(elapsedDays));
                    totalHours.setText(String.valueOf(elapsedHours));
                    totalMinutes.setText(String.valueOf(elapsedMinutes));
                    totalSecond.setText(String.valueOf(elapsedSeconds));


                } catch (ParseException e) {
                    e.printStackTrace();
                }


                getAge();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                edittext2.setText("");
                Toast.makeText(getApplicationContext(), "Successfuly ", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void updateLabel(char c) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (c == 'c')
            edittext1.setText(sdf.format(Calendar.getTime()));
        else
            edittext2.setText(sdf.format(Calendar.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.share:
                doShare();
                return true;
            case R.id.item1:

                return true;
            case R.id.item2:
                doShare();
                return true;
            case R.id.item3:
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                startActivity(launchIntent);
                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item4:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }

                return true;

            case R.id.item5:
                setUrl();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getAge() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long current = 0, births = 0;
        String str = sdf.format(Calendar.getTime());
        String b = str.substring(0, 6) + sdf.format(new Date()).split("/")[2];
        try {
            Date date = sdf.parse(sdf.format(new Date()));
            Date birth = sdf.parse(b);
            current = date.getTime();
            births = birth.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
/**my code
 *
 *
 */
        final class getAge {
            private final int year;
            private final int month;
            private final int day;
            private final int totalDay;

            public getAge(int year, int month, int day, int totalDay) {
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

            public int MonthsToDays(int tMonth, int tYear) {
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

            public getAge(Calendar image1, Calendar image2, int year, int month, int day, int totalDay) {
                this.year = year;
                this.month = month;
                this.day = day;
                this.totalDay = totalDay;
                Calendar mycal1 = (Calendar) image1.clone();
                Calendar mycal2 = (Calendar) image2.clone();

                long d1 = mycal1.getTimeInMillis();
                long d2 = mycal2.getTimeInMillis();

                if (d1 > d2) {
                    mycal1 = (Calendar) image2.clone();
                    mycal2 = (Calendar) image1.clone();
                }

                int mDay = mycal1.get(Calendar.DAY_OF_MONTH);
                int mMonth = mycal1.get(Calendar.MONTH);
                int mYear = mycal1.get(Calendar.YEAR);

                int tDay = mycal2.get(Calendar.DAY_OF_MONTH);
                int tMonth = mycal2.get(Calendar.MONTH);
                int tYear = mycal2.get(Calendar.YEAR);

                int totalDays = gregorianDays(tYear, tMonth, tDay) - gregorianDays(mYear, mMonth, mDay);
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
                        mDayDiff = mDayDiff + MonthsToDays(tMonth - 1, tYear);
                        ;
                    }

                }
                Month3.setText(String.valueOf(month));
                Day3.setText(String.valueOf(day));

            }

            private int gregorianDays(int year, int month, int day) {
                month = (month + 9) % 12;
                year = year - month / 10;
                return 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 1);
            }

        }
}
    public void doShare() {
        // populate the share intent with data
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Put whatever you want");
        startActivity(intent);
    }

    public void setUrl() {
        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?"));
        startActivity(browserIntent);
    }

}
