package com.ernandes;

//License Key Formatting
//Given a string S that consists of only alphanumeric characters and dashes.
// The string is separated into N + 1 groups by N dashes. Also given an integer K.
// We want to reformat the string S, such that each group contains exactly K characters,
// except for the first group, which could be shorter than K but still must contain at least one character.
// Furthermore, a dash must be inserted between two groups, and you should convert
// all lowercase letters to uppercase.
//Examples:
//Input: S = “5F3Z-2e-9-w”, K = 4
//Output: “5F3Z-2E9W”
//Explanation: The string S has been split into two parts,
//each part has 4 characters.
//Note that two extra dashes are not needed and can be removed.
//Input: S = “2-5g-3-J”, K = 2
//Output: “2-5G-3J”
//Explanation: The string s has been split into three parts,
//each part has 2 characters except the first part
//as it could be shorter as mentioned above


public class Main {



    public static void main(String[] args) {

        var s1 = "5F3Z-2e-9-w";
        var s2 = "2-5g-3-J";

        var k1 = 4;
        var k2 = 2;

        System.out.println();

        var results1 = splitString(s1, k1);
        var results2 = splitString(s2, k2);

        System.out.println(results1);
        System.out.println(results2);
    }

    private static String splitString(String s, int k) {

        if(s == null || s.isEmpty() || k <= 0){
            throw new IllegalArgumentException("Invalid input");
        }

        return getStringPart(s , k, true);
    }

    private static String getStringPart(String s, int windowSize, boolean isFirstGroup) {
        if(s.length() < windowSize){
            return s;
        }

        var next = s.indexOf("-");

        if(s.substring(0,next).length() == windowSize){
            return s;
        }

        if (next != -1) {
            s = new StringBuilder(s).deleteCharAt(next).toString();
            s = getStringPart(s.substring(next), windowSize-next, false);
        }


        return s.substring(0, windowSize) + "-" + getStringPart(s.substring(windowSize), windowSize, false);
    }


}


















