package com.tolulope.seamfixtest.model;

import lombok.Data;

@Data
public class VerificationResponse {
    private String code;
    private String message;
    private String bvn;
    private String imageDetails;
    private String basicDetails;

    public VerificationResponse(){}

    public VerificationResponse(String code, String message, String bvn, String imageDetails, String basicDetails) {
        this.code = code;
        this.message = message;
        this.bvn = bvn;
        this.imageDetails = imageDetails;
        this.basicDetails = basicDetails;
    }

    public VerificationResponse(String code, String message, String bvn) {
        this.code = code;
        this.message = message;
        this.bvn = bvn;
    }

    public VerificationResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
