package org.example;

/**
 * Class to make Thread.sleep() easier to use by allowing for seconds and minutes to be used instead of milliseconds.
 * This class isn't really necessary at all but I just find it convenient when making quick changes before I have to do something and don't want to do the calculations.
 * */
public class Wait {
    /**
     * Converts inputted seconds to milliseconds.
     * */
    public static int waitSeconds(int seconds) {
        return seconds * 1000;
    }
    /**
     * Converts inputted minutes to milliseconds.
     * */
    public static int waitMinutes(int minutes) {
        return minutes * 60 * 1000;
    }
}
