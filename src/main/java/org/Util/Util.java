package org.Util;

public class Util {
    public static String toTitleCase(String strIn) {
        if (strIn == null || strIn.isEmpty()) {
            return strIn;
        }
        String[] words = strIn.split(" ");
        String result = "";
        for (String word : words) {
            if (!word.isEmpty()) {
                result += Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase() + " ";
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
