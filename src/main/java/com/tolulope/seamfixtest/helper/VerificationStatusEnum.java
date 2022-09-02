package com.tolulope.seamfixtest.helper;

import lombok.Getter;

@Getter
public enum VerificationStatusEnum {
    SUCCESS("00", "Success"),
    INVALID_BVN("01", "The searched BVN does not exist"),
    INVALID_LENGTH("02", "The searched BVN is invalid"),
    INVALID_CHARACTER("400", "The searched BVN is invalid"),
    IS_EMPTY("400", "One or more of your request parameters failed validation. Please retry");

    private final String code;
    private final String message;

    VerificationStatusEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

}
