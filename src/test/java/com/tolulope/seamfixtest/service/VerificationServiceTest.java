package com.tolulope.seamfixtest.service;

import com.tolulope.seamfixtest.helper.VerificationStatusEnum;
import com.tolulope.seamfixtest.model.VerificationRequest;
import com.tolulope.seamfixtest.model.VerificationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VerificationServiceTest {


    @Test
    public void successful_request() throws Exception {
        VerificationRequest request = new VerificationRequest("12345674123");

        VerificationService verificationService = new VerificationService();

        VerificationResponse response = verificationService.verifyBvn(request);

        assertEquals(VerificationStatusEnum.SUCCESS.getCode(), response.getCode());
        assertEquals(VerificationStatusEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(request.getBvn(), response.getBvn());
        assertNotNull(response.getImageDetails());
        assertNotNull(response.getBasicDetails());

    }

    @Test
    public void test_empty_bvn_in_payload() throws Exception {
        VerificationRequest request = new VerificationRequest("");

        VerificationService verificationService = new VerificationService();

        VerificationResponse response = verificationService.verifyBvn(request);

        assertEquals(VerificationStatusEnum.IS_EMPTY.getMessage(), response.getMessage());
        assertEquals(VerificationStatusEnum.IS_EMPTY.getCode(), response.getCode());
    }

    @Test
    public void test_invalid_bvn_in_payload() throws Exception {
        VerificationRequest request = new VerificationRequest("12324534820");

        VerificationService verificationService = new VerificationService();

        VerificationResponse response = verificationService.verifyBvn(request);

        assertEquals(VerificationStatusEnum.INVALID_BVN.getMessage(), response.getMessage());
        assertEquals(VerificationStatusEnum.INVALID_BVN.getCode(), response.getCode());
    }

    @Test
    public void test_invalid_bvn_length_in_payload() throws Exception {
        VerificationRequest request = new VerificationRequest("1232453480");

        VerificationService verificationService = new VerificationService();

        VerificationResponse response = verificationService.verifyBvn(request);

        assertEquals(VerificationStatusEnum.INVALID_LENGTH.getMessage(), response.getMessage());
        assertEquals(VerificationStatusEnum.INVALID_LENGTH.getCode(), response.getCode());
        assertEquals(request.getBvn(), response.getBvn());
    }

    @Test
    public void test_if_payload_contains_non_digits() throws Exception {
        VerificationRequest request = new VerificationRequest("1123A463480");

        VerificationService verificationService = new VerificationService();

        VerificationResponse response = verificationService.verifyBvn(request);

        assertEquals(VerificationStatusEnum.INVALID_CHARACTER.getMessage(), response.getMessage());
        assertEquals(VerificationStatusEnum.INVALID_CHARACTER.getCode(), response.getCode());
        assertEquals(request.getBvn(), response.getBvn());
    }

    @Test
    public void test_if_payload_is_valid() throws Exception {
        VerificationRequest request = new VerificationRequest("12309873682");

        VerificationService verificationService = new VerificationService();

        VerificationResponse response = verificationService.verifyBvn(request);

        String expected = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/base64Image_two.txt")));

        assertEquals(expected, response.getBasicDetails());

    }

}