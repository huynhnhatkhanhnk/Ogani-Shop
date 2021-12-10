package com.edu.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.NotFoundException;


@Controller
@RequestMapping("send")
public class SendMailController {

	    @Autowired
	    private JavaMailSender mailSender; // Thư viện gửi mail
	    
	    // Show thông tin form
	    @GetMapping("/mail")
	    public String showForgotPasswordForm(Model model) {
	        model.addAttribute("pageTitle", "ForgotPassword");
	        return "/layout/Contact";
	    }
	    // Xử lí thông tin
	    @PostMapping("/send_mail")
	    public String processForgotPassword(HttpServletRequest request, Model model)
	            throws ClassNotFoundException, NotFoundException {
	        String email = request.getParameter("email");
	        String name = request.getParameter("name");
	        String cd = request.getParameter("cd");
	        String mes = request.getParameter("mes");
	       

	        try {	          	           
	            sendEmail(email, mes, cd, name); //Xử lí	            
	            model.addAttribute("message", "Email của bạn đã được gửi. Xin cảm ơn bạn đã góp ý cho Ogani");

	        } catch (Exception ex) {
	            model.addAttribute("error", "Lỗi gửi email. Có thể email bạn nhập chưa chính xác");
	        }

	        return "/layout/Contact";
	    }
	    // Thông tin đc gửi tới gmail khách hàng
	    public void sendEmail(String email, String mes,String cd, String name) throws MessagingException, UnsupportedEncodingException {
	        MimeMessage message = mailSender.createMimeMessage(); 
	        MimeMessageHelper helper = new MimeMessageHelper(message); // thư viện sp định dạng email
	        
	        helper.setFrom(email, "Ogani");
	        helper.setTo("oganishopfpt@gmail.com");
	        
	        String subject = cd;
	        String content = "<p>Email:  "+ email +"</p>"+"<p>Tên khách hàng:  "+ name +"</p>" 
	        +"<p>Nội dung:  "+ mes +"</p>";

	        helper.setSubject(subject);
	        helper.setText(content, true);
	        mailSender.send(message);
	    }

}
