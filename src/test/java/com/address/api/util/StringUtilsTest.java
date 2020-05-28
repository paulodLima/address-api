package com.address.api.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @Test
    void removeNonNumericCharacters() {

        String result = StringUtils.removeNonNumericCharacters("321dsadasdas-.321321--sadsad.sa321.321321321321.321321312");

        assertTrue(org.apache.commons.lang3.StringUtils.isAlphanumeric(result));
        assertEquals("321321321321321321321321321321312", result);

    }
}