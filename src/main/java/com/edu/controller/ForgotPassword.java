package com.edu.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.Utility;
import com.edu.entity.Account;
import com.edu.service.impl.AccountServiceImpl;

import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("account")
public class ForgotPassword {
    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    private JavaMailSender mailSender; // Thư viện gửi mail
    // Show thông tin form
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model) {
        model.addAttribute("pageTitle", "ForgotPassword");
        return "/security/Forgot_ps";
    }
    // Xử lí thông tin
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model)
            throws ClassNotFoundException, NotFoundException {
        String email = request.getParameter("email");
        String token = RandomString.make(30); //random token để lấy token đặt lại mk

        try {
            accountServiceImpl.updateResetToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/account/reset_password?token=" + token; //Dựa vào token đã lưu để show trang reset mk
            sendEmail(email, resetPasswordLink); //Xử lí
            model.addAttribute("message", "Chúng tôi đã gửi một liên kết đặt lại mật khẩu đến email của bạn. Hãy kiểm tra.");

        } catch (Exception ex) {
            model.addAttribute("error", "Lỗi gửi email. Có thể email bạn nhập chưa chính xác");
        }

        return "/security/Forgot_ps";
    }
    // Thông tin đc gửi tới gmail khách hàng
    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage(); 
        MimeMessageHelper helper = new MimeMessageHelper(message); // thư viện sp định dạng email

        helper.setFrom("*****@gmail.com", "Ogani");
        helper.setTo(recipientEmail);

        String subject = "Đây là liên kết để đặt lại mật khẩu của bạn";

        String content = "<p>Xin chào,</p>" + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
                + "<p>Nhấp vào liên kết bên dưới để thay đổi mật khẩu của bạn:</p>" + "<p><a href=\"" + link 
                + "\">Thay đổi mật khẩu của tôi</a> </p>" + "<br>" + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, "
                + "hoặc bạn đã không thực hiện yêu cầu.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Account account = accountServiceImpl.getByResetToken(token);
        // model.addAttribute("token", token);

        if (account == null) {
            model.addAttribute("title", "Reset your password");
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Reset your password");

        return "/security/Change_ps";
    }

    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Account account = accountServiceImpl.getByResetToken(token);
        // Account account = accountServiceImpl.get(token);
        // model.addAttribute("title", "Reset your password");

        if (account == null) {
            model.addAttribute("message", "Reset your password");
            model.addAttribute("message", "Invalid Token");
        } else {
            accountServiceImpl.updatePassword(account, password);
            model.addAttribute("message", "Bạn đã thay đổi thành công mật khẩu của bạn.");
        }
        model.addAttribute("message");
        return "/security/Change_ps";
    }
}
