package com.tolulope.seamfixtest.model;

import lombok.Builder;
import lombok.Data;

@Data
public class VerificationRequest {
    private String bvn;

    public VerificationRequest(){}

    public VerificationRequest(String bvn) {
        this.bvn = bvn;
    }
}
