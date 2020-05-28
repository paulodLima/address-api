package com.address.api.exception;

import com.address.api.util.MessagesUtil;

public class JsonConvertException extends RuntimeException {

    public JsonConvertException(MessageError messageError) {
        super(MessagesUtil.getMessage(messageError));
    }
}
