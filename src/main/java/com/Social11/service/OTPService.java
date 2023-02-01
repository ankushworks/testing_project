package com.Social11.service;


import com.Social11.helper.JwtTokenUtil;
import com.Social11.helper.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    private IjavaMailService otpEmailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public String generateOTP(String email) {
        Long otp = Utils.generateOtp();
        otpEmailService.SendMailToEmail(email, otp);

        // Generate JWT with OTP as the payload
        String jwt = Jwts.builder()
                .setPayload(Long.toString(otp))
                .signWith(SignatureAlgorithm.HS256, "harmeet@123")
                .compact();


        return jwt;
    }

    public boolean verifyOTP(String jwt, int enteredOTP) {
        try {
            // Parse the JWT to get the OTP
            String otp = Jwts.parser()
                    .setSigningKey("secretkey")
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

            return Integer.parseInt(otp) == enteredOTP;
        } catch (Exception e) {
            return false;
        }
    }
}
