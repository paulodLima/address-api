package com.address.api.util;

import com.address.api.dto.FooRequestDTO;
import com.address.api.exception.JsonConvertException;
import com.address.api.exception.MessageErrorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperUtilTest {


    @Test
    void toObjectErrorTest() {

        JsonConvertException jsonConvertException = assertThrows(JsonConvertException.class, () -> ObjectMapperUtil.toObject("", FooRequestDTO.class));

        assertEquals(MessagesUtil.getMessage(MessageErrorImpl.JSON_TO_OBJECT_FAIL), jsonConvertException.getMessage());


    }

    @Test
    void toJson() {

        JsonConvertException jsonConvertException = assertThrows(JsonConvertException.class, () -> ObjectMapperUtil.toJson(new Object()));

        assertEquals(MessagesUtil.getMessage(MessageErrorImpl.OBJECT_TO_JSON_FAIL), jsonConvertException.getMessage());

    }

    @Test
    void getMapper() {

        ObjectMapper mapper = ObjectMapperUtil.getMapper();

        assertNotNull(mapper);
    }
}