package com.tixon.timemanagement;

/**
 * Created by tikhon on 04.03.16.
 */
public class Util {
    public static int[] transformStringToDate(String date) {
        String[] dateArray = date.split("\\.");
        int[] array = new int[dateArray.length];
        for(int i = 0; i < dateArray.length; i++) {
            array[i] = Integer.parseInt(dateArray[i]);
        }
        return array;
    }

    public static int[] transformStringToTime(String time) {
        String[] timeArray = time.split(":");
        int[] array = new int[timeArray.length];
        for (int i = 0; i < timeArray.length; i++) {
            array[i] = Integer.parseInt(timeArray[i]);
        }
        return array;
    }
}
