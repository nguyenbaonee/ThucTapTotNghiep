package com.airplane.schedule.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    public void sendMailForResetPassWord(String toEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Reset Password Iam");
        message.setText("Vui lòng click vào link sau để reset mật khẩu: " + resetLink);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendMailOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("OTP Iam");
        message.setText("Mã OTP của bạn là: " + otp);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendMailAlert(String toEmail, String type) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        switch (type) {
            case "signin":
                message.setSubject("Chúc mừng bạn đăng ký thành công");
                message.setText("Chúc mừng bạn đăng ký thành công. Tài khoản của bạn đã có thể đăng nhập vào hệ thống");
                break;
            case "change_info":
                message.setSubject("Thay đổi thông tin thành công");
                message.setText("Chúc mừng bạn đã thay đổi thông tin thành công");
                break;
            case "change_password":
                message.setSubject("Thay đổi mật khẩu thành công");
                message.setText("Chúc mừng bạn đã thay đổi mật khẩu thành công");
                break;
            default:
                message.setSubject("Alert Iam");
                message.setText("Tài khoản của bạn đã thực hiện thay đổi thành công");
        }
        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}