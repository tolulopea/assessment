package com.tolulope.seamfixtest.service;

import com.tolulope.seamfixtest.helper.VerificationStatusEnum;
import com.tolulope.seamfixtest.model.VerificationRequest;
import com.tolulope.seamfixtest.model.VerificationResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class VerificationService {

    private static final Set<String> validBvn = new HashSet<>(Arrays.asList("12345674123", "12309873682", "12459873452", "12356713682"));


    public VerificationResponse verifyBvn(VerificationRequest request) {
        if (request.getBvn() == null || request.getBvn().isEmpty()) {
            return new VerificationResponse(
                    VerificationStatusEnum.IS_EMPTY.getCode(),
                    VerificationStatusEnum.IS_EMPTY.getMessage()
            );
        }

        if (request.getBvn().length() < 11) {
            return new VerificationResponse(
                    VerificationStatusEnum.INVALID_LENGTH.getCode(),
                    VerificationStatusEnum.INVALID_LENGTH.getMessage(),
                    request.getBvn());
        }

        if (!request.getBvn().matches("[0-9]+")) {
            return new VerificationResponse(
                    VerificationStatusEnum.INVALID_CHARACTER.getCode(),
                    VerificationStatusEnum.INVALID_CHARACTER.getMessage(),
                    request.getBvn());
        }

        if (!validBvn.contains(request.getBvn())) {
            return new VerificationResponse(
                    VerificationStatusEnum.INVALID_BVN.getCode(),
                    VerificationStatusEnum.INVALID_BVN.getMessage(),
                    request.getBvn());
        }

        String imageDetail = null;
        String basicDetail = null;
        try {
            imageDetail = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/base64Image.txt")));
            basicDetail = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/base64Image_two.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new VerificationResponse(
                VerificationStatusEnum.SUCCESS.getCode(),
                VerificationStatusEnum.SUCCESS.getMessage(),
                request.getBvn(),
                imageDetail,
                basicDetail
        );
    }

}
