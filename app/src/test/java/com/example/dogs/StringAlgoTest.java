package com.example.dogs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringAlgoTest {
    @Test
    public void testVowelCount() {
        assertEquals(StringAlgo.getVowelCount(null), 0);
        assertEquals(StringAlgo.getVowelCount(""), 0);
        assertEquals(StringAlgo.getVowelCount("Hello"), 2);
        assertEquals(StringAlgo.getVowelCount("1234567890"), 0);
    }

    @Test
    public void testGetConsonantCount() {
        assertEquals(StringAlgo.getConsonantCount(null), 0);
        assertEquals(StringAlgo.getConsonantCount(""), 0);
        assertEquals(StringAlgo.getConsonantCount("Hello"), 3);
        assertEquals(StringAlgo.getConsonantCount("1234567890"), 0);
    }

    @Test
    public void checkIsPalindrome() {
        assertFalse(StringAlgo.isPalindrome(null));
        assertFalse(StringAlgo.isPalindrome(""));
        assertTrue(StringAlgo.isPalindrome("malayalam"));
        assertFalse(StringAlgo.isPalindrome("india"));
    }
}
