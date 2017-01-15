package com.devangam.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.devangam.dto.Mail;

@Service("emailService")
public class EmailService {


	@Autowired(required=true)
    private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public static final String VERIFY_EMAIL = "VERIFY_EMAIL";
	public static final String RESET_PASSWORD = "RESET_PASSWORD";
	
	
	private Map<String,String> templateMap = new HashMap<String, String>();

	public EmailService()
	{
		templateMap.put(EmailService.VERIFY_EMAIL + "_SUBJECT", "Please verify your email address");
		templateMap.put(EmailService.VERIFY_EMAIL + "_TEMPLATE", "Email_Verification.vm");
		templateMap.put("EMAIL_FROM", "renu.javatechnews@gmail.com");
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
        properties.setProperty("mail.smtp.ssl.enable", "true");
        
        return properties;
    }
	@Bean
	public JavaMailSenderImpl mailSender() {
			JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		    javaMailSender.setProtocol("smtp");
		    javaMailSender.setHost("smtp.gmail.com");
		    javaMailSender.setPort(587);
		    javaMailSender.setUsername("renu.javatechnews@gmail.com");
		    javaMailSender.setPassword("RenuAmmu9703");
		    javaMailSender.setJavaMailProperties(getMailProperties());

		    return javaMailSender;
	}
	

}
