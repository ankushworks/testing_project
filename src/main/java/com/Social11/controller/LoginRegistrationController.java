package com.Social11.controller;

import com.Social11.Dao.IuserRepository;
import com.Social11.helper.Utils;
import com.Social11.models.UserEntity;
import com.Social11.service.IjavaMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginRegistrationController {

    @Autowired
    IjavaMailService mailService;

//	@Autowired
//	UserProfileEntity userProfile;

    @Autowired
    IuserRepository Repository;

    @Autowired
    private PasswordEncoder bcyrtp;


    @GetMapping("otpform")
    public String otpform() {
        return "otp";
    }

    @PostMapping("/otpVerification")
    public Map<String, String> verifyOtp(HttpServletRequest request, HttpSession session) {
        Map<String, String> mp = new HashMap<>();
        String otp = (String) request.getParameter("otp");
        System.out.println(otp);
        Long otp1 = Long.valueOf(otp);
        System.out.println(otp1);
        Long OTPcode = (Long) session.getAttribute("generatedOtp");
        System.out.println(OTPcode);
        if (OTPcode.equals(otp1)) {
            UserEntity entity = new UserEntity();
            entity.setEmail_address((String) session.getAttribute("email"));
            entity.setCountry((String) session.getAttribute("country"));
            entity.setDateandtime((String) session.getAttribute("date"));
            entity.setEnabled(true);
            entity.setFirstname((String) session.getAttribute("firstname"));
            entity.setId((Integer) session.getAttribute("id"));
            entity.setLastname((String) session.getAttribute("lastname"));
            entity.setPassword(bcyrtp.encode((String) session.getAttribute("password")));
            entity.setRole((String) session.getAttribute("role"));
            entity.setUsername((String) session.getAttribute("username"));
            Repository.save(entity);
            mp.put("text", "User Saved Succesfully");
            mp.put("response", "200 OK");
            return mp;
        } else {
            mp.put("text", "Incorrect otp");
            mp.put("response", "400 Bad Request");
            return mp;
        }
    }


    @GetMapping("/forgot")
    public String otpform(Principal principal) {
        System.out.println("hello");
        return "forgotform";
    }

    @PostMapping("/verifyOtp")
    public Map<String, String> otpverify(@RequestParam("email") String email, HttpSession session) {
        Map<String, String> mp = new HashMap<>();
        System.out.println("Otp form page " + email);
        Long OTPcode = (long) ((Math.random() * 9 * Math.pow(5, 6)) + Math.pow(5, 6));
        UserEntity entity = Repository.findByemail(email);
        session.setAttribute("Otp", OTPcode);
        session.setAttribute("Email", email);
        // In order to confirm the user is registered user or not we send otp in registered email
        if (entity != null) {
            mp.put("text", "email send succesfully");
            mp.put("response", "200 OK");
            String message = mailService.SendMailToEmail(email, OTPcode);
            return mp;
        } else {
            System.out.println("User does not exist with this email");
            mp.put("text", "user does not exist with this email");
            mp.put("response", "400 Bad Request");
            return mp;
        }
    }

    @PostMapping("/checkotp")
    public Map<String, String> checkOtp(HttpSession session, HttpServletRequest request) {
        Map<String, String> mp = new HashMap<>();
        Long otp = Long.valueOf(request.getParameter("otp"));
        System.out.println(otp);
        Long Otp = (Long) (session.getAttribute("Otp"));
        System.out.println(Otp);
        if (otp.equals(Otp)) {
            mp.put("text", "Otp is Correct");
            mp.put("response", "Success");
            return mp;
        } else {
            System.out.println("Incorrect otp");
            mp.put("text", "incorrect otp");
            return mp;
        }
    }

    @PostMapping("/changePassword")
    public Map<String, String> changepassword(HttpServletRequest request, HttpSession session) {
        Map<String, String> mp = new HashMap<>();
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        if (password.equals(cpassword)) {
            try {
                String email = (String) session.getAttribute("Email");
                System.out.println();
                UserEntity user = Repository.findByemail(email);
                user.setPassword(bcyrtp.encode(password));
                this.Repository.save(user);
                System.out.println("password changed");
                mp.put("text", "Password Changed Succesfully");
                mp.put("response", "200");
                return mp;
            } catch (Exception e) {
                mp.put("text", "User not found");
                return mp;
            }
        } else {
            mp.put("text", "password is not matched");
            return mp;
        }
    }


//	----------------- added by ankush -------


    @PostMapping("/forget_password")
    public Map<String, String> forgetPassword(@RequestParam("email") String email, HttpSession session) {
        Map<String, String> mp = new HashMap<>();
        if (!email.isEmpty()) {
            if (Repository.findByemail(email) != null) {
                Long otp = Utils.generateOtp();
                System.out.println(otp);
                session.setAttribute("generatedOtp", otp);
                session.setAttribute("email", email);
                mailService.SendMailToEmail(email, otp);
                mp.put("message", "Otp Send Successfully check your email for otp");
                mp.put("success", "true");

            } else {
                mp.put("message", "Invalid email address");
                mp.put("success", "false");
            }

        } else {
            mp.put("message", "Email may not be blank");
            mp.put("success", "false");
        }

        return mp;
    }


    @PostMapping("/create_user")
    public Map<String, String> createUser(@RequestBody UserEntity userprofile, HttpSession session) {
        Map<String, String> mp = new HashMap<>();
        String message;

        try {
            if (!userprofile.getEmail_address().isEmpty() && !userprofile.getPassword().isEmpty() && !userprofile.getUsername().isEmpty()) {

                if (Repository.findByemail(userprofile.getEmail_address()) != null || Repository.findByusername(userprofile.getUsername()) != null) {
                    System.out.println();
                    mp.put("message", "User with this email or username already exist ");
                    return mp;
                } else {
                    System.out.println(userprofile.getEmail_address());
                    Long otp = Utils.generateOtp();
                    message = mailService.SendMailToEmail(userprofile.getEmail_address(), otp);
                    session.setAttribute("generatedOtp", otp);


                    if (message != null) {
                        userprofile.setDateandtime(Utils.getCurrentDateAndTime());
                        userprofile.setRole("user");
                        session.setAttribute("email", userprofile.getEmail_address());
                        session.setAttribute("date", userprofile.getDateandtime());
                        session.setAttribute("role", userprofile.getRole());
                        session.setAttribute("country", userprofile.getCountry());
                        session.setAttribute("firstname", userprofile.getFirstname());
                        session.setAttribute("id", userprofile.getId());
                        session.setAttribute("lastname", userprofile.getLastname());
                        session.setAttribute("username", userprofile.getUsername());
                        session.setAttribute("password", userprofile.getPassword());
                        mp.put("text", "Otp Send Succesfully check your email for otp");
                        mp.put("response", "200 OK");
                        return mp;
                    } else {
                        mp.put("text", "Opps something went wrong opt not send");
                        mp.put("response", "400 Bad Request");
                        return mp;
                    }
                }
            } else {
                mp.put("message", "fields cannot be empty");
                return mp;
            }

        } catch (Exception ex) {
            System.out.println("Exception while creating account");
            mp.put("text", "Exception occur");
            return mp;
        }
    }


    @PostMapping("/verify_otp")
    public Map<String, String> verifyOtpNew(HttpServletRequest request, HttpSession session) {

        Map<String, String> mp = new HashMap<>();

        Long otpFromClient = Long.valueOf(request.getParameter("otp"));
        Long optFromSession = (Long) session.getAttribute("generatedOtp");


        if (otpFromClient.equals(optFromSession)) {

            UserEntity userEntity;
            userEntity = Repository.findByemail((String) session.getAttribute("email"));
            if (userEntity != null) {
                // forget user
                mp.put("otp", "verified");


            } else {

                // new user
                userEntity = new UserEntity();
                userEntity.setEmail_address((String) session.getAttribute("email"));
                userEntity.setCountry((String) session.getAttribute("country"));
                userEntity.setDateandtime((String) session.getAttribute("date"));
                userEntity.setEnabled(true);
                userEntity.setFirstname((String) session.getAttribute("firstname"));
                userEntity.setId((Integer) session.getAttribute("id"));
                userEntity.setLastname((String) session.getAttribute("lastname"));
                userEntity.setPassword(bcyrtp.encode((String) session.getAttribute("password")));
                userEntity.setRole((String) session.getAttribute("role"));
                userEntity.setUsername((String) session.getAttribute("username"));
                Repository.save(userEntity);

                mp.put("text", "User Saved Succesfully");
            }
            mp.put("success", "true");

        } else {
            mp.put("text", "Incorrect otp");
            mp.put("response", "400 Bad Request");
            mp.put("success", "false");
        }
        return mp;
    }



	@PostMapping("/reset_password")
	public Map<String, String> changePasswordNew(HttpServletRequest request, HttpSession session) {
		Map<String, String> mp = new HashMap<>();
		String password = request.getParameter("password");
		if(!password.isEmpty()){

		    try {

                String email = (String) session.getAttribute("email");
                UserEntity userEntity  = Repository.findByemail(email);
                userEntity.setPassword(bcyrtp.encode(password));
                Repository.save(userEntity);

                System.out.println(userEntity);
                System.out.println("User Password Changed!!");

                mp.put("message" , "User password changed successfully");
                mp.put("success" , "true");

            }catch (Exception e){
                mp.put("message" , "exception " + e.getLocalizedMessage());
                mp.put("success" , "false");
            }

        }else{
		    mp.put("message" , "field cannot be empty");
		    mp.put("success" , "false");
        }

		return mp;
	}

}
