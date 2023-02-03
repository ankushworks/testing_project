package com.Social11.controller;

import com.Social11.Dao.IuserEntityTemp;
import com.Social11.Dao.IuserRepository;
import com.Social11.helper.Utils;
import com.Social11.models.UserEntity;
import com.Social11.models.UserEntityTemp;
import com.Social11.service.IjavaMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    private IuserEntityTemp tempentity;

    @GetMapping("/createUser")
    public String createUser() {
        return "User Account page";
    }

//	@PostMapping("/CreateProfile")
//	public Map<String,String> createUser(@RequestBody UserEntityTemp userprofile) {
//		Map<String,String> mp = new HashMap<>();
//		String message;
//		// check email is already present in database or not
//		try {
//			UserEntity existinguser=Repository.findByusername(userprofile.getUsername());
//			String email = userprofile.getEmail_address();
//			if(existinguser!=null) {
//				// user already exist
//				mp.put("text", "User already exist");
//				return mp;
//			}
//			Long OTPcode  = (long)((Math.random()*9*Math.pow(5,6))+Math.pow(5,6));
//			System.out.println(email);
//			System.out.println(OTPcode);
//			message =  mailService.SendMailToEmail(email,OTPcode);
////			session.setAttribute("generatedOtp", OTPcode);
//			userprofile.setOtp(OTPcode);
//
//			if(message!="") {
//			// Saving user data into database
//			// userprofile.setPassword(encoder.encode(userprofile.getPassword()));
//
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//			LocalDateTime now = LocalDateTime.now();
//
//			userprofile.setDateandtime(dtf.format(now));
//			tempentity.save(userprofile);
//			mp.put("text", "Otp Send Succesfully check your email for otp");
//			mp.put("response", "200 OK");
//			mp.put("email", userprofile.getEmail_address());
//			return mp;
//			}
//			else {
//				mp.put("text", "Opps something went wrong opt not send");
//				mp.put("response", "400 Bad Request");
//				return mp;
//			}
//		}
//		catch(Exception e) {
//			System.out.println("Exception while creating account");
//			mp.put("text", "Exception occur");
//			return mp;
//		}
//	}

//    @GetMapping("otpform")
//    public String otpform() {
//        return "otp";
//    }
//
//    @PostMapping("/otpVerification")
//    public Map<String, String> verifyOtp(@RequestParam("otp") long otp, @RequestParam("email") String email) {
//        Map<String, String> mp = new HashMap<>();
//        System.out.println("hello");
//        System.out.println("my otp is :" + otp + " email is :" + email);
//        try {
//            if (email != "") {
//                System.out.println("working succesfully");
//                UserEntityTemp userTemp = this.tempentity.findByemail(email);
//                Long otp1 = Long.valueOf(otp);
//                System.out.println(userTemp);
//                Long OTPcode = userTemp.getOtp();
//                System.out.println(OTPcode);
//                if (OTPcode.equals(otp1)) {
//                    UserEntity entity = new UserEntity();
//                    entity.setEmail_address(userTemp.getEmail_address());
//                    entity.setUsername(userTemp.getUsername());
//                    entity.setPassword(bcyrtp.encode(userTemp.getPassword()));
//                    entity.setDateandtime(userTemp.getDateandtime());
//                    entity.setFirstname(userTemp.getFirstname());
//                    Repository.save(entity);
//                    System.out.println("working or not");
//                    this.tempentity.deletebyemail(email);
//                    System.out.println("noot workign");
//                    mp.put("text", "User Saved Succesfully");
//                    mp.put("response", "200 OK");
//                    return mp;
//                } else {
//                    mp.put("text", "Incorrect otp");
//                    mp.put("response", "400 Bad Request");
//                    return mp;
//                }
//            } else {
//                Map<String, String> mp1 = new HashMap<>();
//                mp1.put("Error", "Email cannot be empty");
//                return mp1;
//            }
//        } catch (Exception e) {
//            System.out.println("Error occured " + e);
//            Map<String, String> mp1 = new HashMap<>();
//            mp1.put("error", "Incorrect email");
//            return mp1;
//        }
//    }
//
//    @GetMapping("/forgot")
//    public String otpform(Principal principal) {
//        System.out.println("hello");
//        return "forgotform";
//    }
//
//    @PostMapping("/verifyOtp")
//    public Map<String, String> otpverify(@RequestParam("email") String email) {
//        Map<String, String> mp = new HashMap<>();
//        System.out.println("Otp form page " + email);
//        Long OTPcode = (long) ((Math.random() * 9 * Math.pow(5, 6)) + Math.pow(5, 6));
//        UserEntity entity = Repository.findByemail(email);
//        // In order to confirm the user is registered user or not we send otp in registered email
//        if (entity != null) {
//            UserEntityTemp tentity = new UserEntityTemp();
//            tentity.setEmail_address(entity.getEmail_address());
//            tentity.setOtp(OTPcode);
//            this.tempentity.save(tentity);
//            mp.put("text", "email send succesfully");
//            mp.put("email", entity.getEmail_address());
//            mp.put("response", "200 OK");
//            String message = mailService.SendMailToEmail(email, OTPcode);
//            return mp;
//        } else {
//            System.out.println("User does not exist with this email");
//            mp.put("text", "user does not exist with this email");
//            mp.put("response", "400 Bad Request");
//            return mp;
//        }
//    }
//
//    @PostMapping("/checkotp")
//    public Map<String, String> checkOtp(@RequestParam("otp") long otp, @RequestParam("email") String email) {
//        Map<String, String> mp = new HashMap<>();
//        UserEntityTemp entity = this.tempentity.findByemail(email);
//        Long Otp = entity.getOtp();
//        Long otp1 = Long.valueOf(otp);
//        if (otp1.equals(Otp)) {
//            mp.put("text", "Otp is Correct");
//            mp.put("email", entity.getEmail_address());
//            mp.put("response", "Success");
//            return mp;
//        } else {
//            System.out.println("Incorrect otp");
//            mp.put("text", "incorrect otp");
//            return mp;
//        }
//    }
//
//    @PostMapping("/changePassword")
//    public Map<String, String> changepassword(@RequestParam
//                                                      ("password") String password, @RequestParam("cpassword") String cpassword,
//                                              @RequestParam("email") String email) {
//        Map<String, String> mp = new HashMap<>();
//        if (password.equals(cpassword)) {
//            try {
//                UserEntity user = Repository.findByemail(email);
//                user.setPassword(bcyrtp.encode(password));
//                this.Repository.save(user);
//                this.tempentity.deletebyemail(email);
//                System.out.println("password changed");
//                mp.put("text", "Password Changed Succesfully");
//                mp.put("response", "200");
//                return mp;
//            } catch (Exception e) {
//                mp.put("text", "User not found");
//                return mp;
//            }
//        } else {
//            mp.put("text", "password is not matched");
//            return mp;
//        }
//    }



//	added by ankush ----

    @PostMapping("/create_user")
    public Map<String, String> createUser(@RequestBody UserEntityTemp userprofile) {
        Map<String, String> mp = new HashMap<>();
        String message;


        try {
            if (!userprofile.getEmail_address().isEmpty() && !userprofile.getPassword().isEmpty() && !userprofile.getUsername().isEmpty()) {
                System.out.println("TestingForExist" + Repository.findByusername(userprofile.getUsername()));
                if (Repository.findByemail(userprofile.getEmail_address()) != null || Repository.findByusername(userprofile.getUsername()) != null) {
                    mp.put("message", "User with this email or username already exist ");
                    return mp;
                } else {

                    Long otp = Utils.generateOtp();

                    message = mailService.SendMailToEmail(userprofile.getEmail_address(), otp);
                    userprofile.setOtp(otp);


                    if (message != null) {
                        userprofile.setDateandtime(Utils.getCurrentDateAndTime());
                        if (tempentity.findByemail(userprofile.getEmail_address()) == null) {
                            tempentity.save(userprofile);
                        } else {
                            tempentity.deletebyemail(userprofile.getEmail_address());
                            tempentity.save(userprofile);
                        }
                        mp.put("email", userprofile.getEmail_address());
                        mp.put("success", "true");
                        mp.put("message", "Otp Send Succesfully check your email for otp");
                        mp.put("response", "200 OK");
                        return mp;
                    } else {
                        mp.put("success", "false");
                        mp.put("message", "Opps something went wrong opt not send");
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


    @PostMapping("/forget_password")
    public Map<String, String> forgetPassword(@RequestParam("email") String email) {
        Map<String, String> mp = new HashMap<>();
        UserEntityTemp userEntityTemp = new UserEntityTemp();
        if (!email.isEmpty()) {
            if (Repository.findByemail(email) != null) {
                Long otp = Utils.generateOtp();
                System.out.println(otp);
                userEntityTemp.setEmail_address(email);
                userEntityTemp.setOtp(otp);

                //TODO bug need to resolve
                if (tempentity.findByemail(userEntityTemp.getEmail_address()) != null) {
                    tempentity.deletebyemail(userEntityTemp.getEmail_address());
                }
                tempentity.save(userEntityTemp);

                mailService.SendMailToEmail(email, otp);
                mp.put("email", email);
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


    @PostMapping("/verify_otp")
    public Map<String, String> verifyOtpNew(@RequestParam("otp") long otp, @RequestParam("email") String email) {

        Map<String, String> mp = new HashMap<>();

        Long otpFromClient = Long.valueOf(otp);
        String emailFromClient = String.valueOf(email);


        System.out.println("my otp is :" + otpFromClient + " email is :" + emailFromClient);

        if (!emailFromClient.isEmpty()) {

            UserEntityTemp userEntityTemp = this.tempentity.findByemail(emailFromClient);

            Long otpFromDb = userEntityTemp.getOtp();

            System.out.println("otpformCLient" + otpFromClient);
            System.out.println("otpformdb" + otpFromDb);


            if (otpFromClient.equals(otpFromDb)) {

                System.out.println("Otp Verified");


                UserEntity userEntity;
                userEntity = Repository.findByemail((String) userEntityTemp.getEmail_address());
                if (userEntity != null) {

                    // forget user
                    mp.put("otp", "verified");
                    mp.put("email" , userEntity.getEmail_address());

                } else {

                    // new user

                    UserEntity entity = new UserEntity();
                    entity.setEmail_address(userEntityTemp.getEmail_address());
                    entity.setUsername(userEntityTemp.getUsername());
                    entity.setPassword(bcyrtp.encode(userEntityTemp.getPassword()));
                    entity.setDateandtime(userEntityTemp.getDateandtime());
                    entity.setFirstname(userEntityTemp.getFirstname());
                    Repository.save(entity);

                    mp.put("email" , entity.getEmail_address());
                    mp.put("text", "User Saved Successfully");
                }
                this.tempentity.deletebyemail(emailFromClient);
                mp.put("success", "true");

            } else {
                mp.put("text", "Incorrect otp");
                mp.put("response", "400 Bad Request");
                mp.put("success", "false");
            }
        } else {
            mp.put("success", "false");
            mp.put("message", "fields cannot be empty");
            return mp;
        }
        return mp;
    }


    @PostMapping("/reset_password")
    public Map<String, String> changePasswordNew(@RequestParam("password") String password, @RequestParam("email") String email) {
        Map<String, String> mp = new HashMap<>();

        if (!password.isEmpty() && !email.isEmpty()) {

            try {

                UserEntity userEntity = Repository.findByemail(email);
                userEntity.setPassword(bcyrtp.encode(password));
                Repository.save(userEntity);
                System.out.println(userEntity);
                System.out.println("User Password Changed!!");
                if(tempentity.findByemail(email) != null){
					this.tempentity.deletebyemail(email);
				}

                mp.put("message", "User password changed successfully");
                mp.put("success", "true");

            } catch (Exception e) {
                mp.put("message", "exception " + e.getLocalizedMessage());
                mp.put("success", "false");
            }

        } else {
            mp.put("message", "field cannot be empty");
            mp.put("success", "false");
        }

        return mp;
    }


}