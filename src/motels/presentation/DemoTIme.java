package motels.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DemoTIme {


    public static void main(String[] args) {
        String dateStart = "12/03/14 13h";
        String dateStop = "12/03/14 23";

// Custom date format
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// Get msec from each, and subtract.
        long diff = d2.getTime() - d1.getTime();

        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (60 * 60 * 1000 * 24);

        System.out.println("Time in hours: " + diffHours + " hours.");
        System.out.println("Time in days: " + diffDays + " days.");
        System.out.println("-----------");
        int hour = 12;
        String a = "saf"+hour;
        System.out.println(a);

        Date date = new Date();
        System.out.println(format.format(date));
    }


}

