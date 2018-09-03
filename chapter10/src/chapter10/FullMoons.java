package chapter10;

import java.util.Calendar;

public class FullMoons {

    private final static int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;
    private final static float FULL_MOON_PERIOD = 29.52f;

    public static void main() {
        Calendar c = Calendar.getInstance();
        c.set(2004, 0, 7, 15, 40);
        long day1 = c.getTimeInMillis();

        for (int i = 0; i < 60; i++) {
            day1 += (long) (DAY_IN_MILLISECONDS * FULL_MOON_PERIOD);
            c.setTimeInMillis(day1);
            System.out.println(String.format("full moon on %tc", c));
        }
    }
}
