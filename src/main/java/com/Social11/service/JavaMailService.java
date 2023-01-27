package com.Social11.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class JavaMailService implements IjavaMailService {

	@Override
	public String SendMailToEmail(String email,long otp) {
		String result;
		String message="Email Verification Confirmation";
        String subject= "Your OTP is:"+otp;
        String to = email;
        String from= "roshanawal231@gmail.com";
        sendmail(message,subject,from,to);	
        if(isMessageSend) {
        	result="Message send Succesfully";
        }
        else{
        	result="";
        }
        return result;
	}
	public static boolean isMessageSend=false;
	public static void sendmail(String message,String subject,String from,String to) {
		String host="smtp.gmail.com";
    	
		/* get the system properties */
    	Properties properties = System.getProperties();
    	System.out.println("properties is:"+properties);
    	
		/* host set */
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");
    	
		/* Step:1 to get the session object */
    	Session session=Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("roshanawal231@gmail.com","kufqrgsybkwzkmgo");
			}
    		
    	});
    	session.setDebug(true);
    	
		/* step:2 compose the message */
    	MimeMessage m =new MimeMessage(session);
    	 try {
    		 m.setFrom(from);
    		 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		 m.setSubject(subject);
    		 m.setText(message);
    		 Transport.send(m);
    		 System.out.println("send successfully");
    		 isMessageSend=true;
    	 }
    	 catch(Exception e) {
    		 System.out.println("incorrect something");
    	 }
  

	}
}
