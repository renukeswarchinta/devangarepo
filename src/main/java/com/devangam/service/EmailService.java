package com.devangam.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.devangam.dto.EmailOrMobileOtpDTO;
import com.devangam.dto.Mail;
import com.devangam.entity.Otp;
import com.devangam.entity.User;
import com.devangam.utils.PasswordProtector;

@Service("emailService")
public class EmailService {


	@Autowired(required=true)
    private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private OTPService otpService;

	public static final String VERIFY_EMAIL = "VERIFY_EMAIL";
	public static final String RESET_PASSWORD = "RESET_PASSWORD";
	
	
	private Map<String,String> templateMap = new HashMap<String, String>();

	public EmailService()
	{
		templateMap.put(EmailService.VERIFY_EMAIL + "_SUBJECT", "Please verify your email address");
		templateMap.put(EmailService.VERIFY_EMAIL + "_TEMPLATE", "Email_Verification.vm");
		templateMap.put("EMAIL_FROM", "contact@devanga.org");
	}
	
	public void sendEmailForVerification(EmailOrMobileOtpDTO emailOrMobileOtpDTO){
		User repositoryUser = emailOrMobileOtpDTO.getUser();
		
		Otp emailOTP = new Otp();
		emailOTP.setUserId(repositoryUser.getUserId());
		emailOTP.setVerificationId(repositoryUser.getEmail());
		emailOTP.setType("EMAIL");
		otpService.reCreateOTP(emailOTP);
		
		 Mail mail = new Mail();
		 mail.setTemplateName(EmailService.VERIFY_EMAIL);
		 mail.setMailTo(emailOrMobileOtpDTO.getUser().getEmail());
		 Map<String,String> map =new HashMap<String,String>();
		 final String token = emailOTP.getOtp();
		 map.put("firstName",  repositoryUser.getFirstname());
		 map.put("link", PasswordProtector.decrypt(token));
		 mail.setValueMap(map);
		 sendMail(mail);
	}
	
	

	public void sendMail(Mail mail)
	{
		try
		{

		  MimeMessage mimeMessage = mailSender().createMimeMessage();
		  MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		  helper.setFrom(templateMap.get("EMAIL_FROM"));
		  if(mail.getMailTo() != null && mail.getMailTo().length() > 0)
		  {
			  helper.setTo(mail.getMailTo());
			  if(mail.getMailBcc() != null && mail.getMailBcc().length() > 0)
			  {
				  helper.setBcc(mail.getMailBcc());
			  }
			  if(mail.getMailCc() != null && mail.getMailCc().length() > 0)
			  {
				  helper.setCc(mail.getMailCc());
			  }
			  helper.setSubject(templateMap.get(mail.getTemplateName() + "_SUBJECT"));


			  Template template = velocityEngine.getTemplate(templateMap.get(mail.getTemplateName() + "_TEMPLATE"));
			  VelocityContext velocityContext = new VelocityContext();
			  Map<String,String> valueMap = mail.getValueMap();
			  if(valueMap != null)
			  {
				  for(String key :valueMap.keySet())
				  {
					  velocityContext.put(key, valueMap.get(key));
				  }
			  }
			  velocityContext.put("TITLE",templateMap.get(mail.getTemplateName() + "_SUBJECT"));
			  velocityContext.put("emailAddress",mail.getValueMap().get("emailAddress"));
			  velocityContext.put("message",mail.getValueMap().get("message"));
			  velocityContext.put("mobileNumber",mail.getValueMap().get("mobileNumber"));
			  velocityContext.put("name",mail.getValueMap().get("name"));
			  velocityContext.put("link",mail.getValueMap().get("link"));
			  StringWriter stringWriter = new StringWriter();
			  template.merge(velocityContext, stringWriter);
			  mimeMessage.setContent(stringWriter.toString(), "text/html");
			  mailSender().send(mimeMessage);
		  }
		  
		
		}
		catch(Exception e)
		{
			//log.error("Send Email Error ", e);
		}

	}
	private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        //properties.setProperty("mail.smtp.ssl.enable", "true");
        
        return properties;
    }
	@Bean
	public JavaMailSenderImpl mailSender() {
			JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		    javaMailSender.setProtocol("smtp");
		    javaMailSender.setHost("smtp.devanga.org");
		    javaMailSender.setPort(587);
		    javaMailSender.setUsername("contact@devanga.org");
		    javaMailSender.setPassword("nS*#RS$@1");
		    javaMailSender.setJavaMailProperties(getMailProperties());

		    return javaMailSender;
	}
	

}
