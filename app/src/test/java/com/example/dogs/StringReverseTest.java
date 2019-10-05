package com.example.dogs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StringReverseTest {
    @Test
    public void reverseNullString() {
        assertNull(StringReverse.reverse(null));
    }

    @Test
    public void reverseEmptyString() {
        assertEquals(StringReverse.reverse(""), "");
    }

    @Test
    public void reverseString() {
        assertEquals(StringReverse.reverse("Hello World!"), "!dlroW olleH");
    }

    @Test
    public void reverseString_1() {
        assertEquals(StringReverse.reverse("Malayalam"), "malayalaM");
    }
}
