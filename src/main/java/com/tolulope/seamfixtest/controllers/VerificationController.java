package com.tolulope.seamfixtest.controllers;

import com.tolulope.seamfixtest.model.VerificationRequest;
import com.tolulope.seamfixtest.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verify")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/bvn")
    public ResponseEntity<?> verifyBVN(@RequestBody VerificationRequest request) {
        return new ResponseEntity<>(verificationService.verifyBvn(request), HttpStatus.OK);
    }
}
