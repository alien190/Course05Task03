package com.example.alien.course04task02.utils;

import java.util.Locale;

public class StringUtils {
    public static String rateToString(double rate) {
        return String.format(Locale.ENGLISH, "%.1f", rate);
    }
}
