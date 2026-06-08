package com.dsa.iterator.problems;

// You’re given a compressed string like:

// "a2b3"

// Meaning:

// "aabbb"

// You need to implement:

// next()    → return next character
// hasNext() → check if more characters exist

public class StringIterator {

    private String s;
    private int index;
    private char currChar;
    private int count;

    public StringIterator(String compressedString) {
        this.s = compressedString;
        this.index = 0;
        this.count = 0;
    }

    public char next() {
        if (!hasNext()) return ' ';

        if (count == 0) {
            currChar = s.charAt(index++);
            count = 0;

            // parse number
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                count = count * 10 + (s.charAt(index) - '0');
                index++;
            }
        }

        count--;
        return currChar;
    }

    public boolean hasNext() {
        return count > 0 || index < s.length();
    }
}

