package jmaster.io.notificationservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jmaster.io.notificationservice.config.ApplicationConfig;
import jmaster.io.notificationservice.model.MessageDTO;
import jmaster.io.notificationservice.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final ApplicationConfig applicationConfig;

    private final JavaMailSender javaMailSender;

//    Để đọc các email template
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    @Async //Tạo luồng khác để phục vụ cho việc gửi email để tránh việc block nhau.
    public void sendEmail(MessageDTO messageDTO) {
        try {
            log.info("START... Sending email");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

//            Load template email with content
            Context context = new Context();
            context.setVariable("name", messageDTO.getToName());
            context.setVariable("content", messageDTO.getContent());
            String html = springTemplateEngine.process("Welcome-email", context);

//            Send email
            helper.setTo(messageDTO.getTo());
            helper.setText(html, true);
            helper.setSubject(applicationConfig.getFrom());
            javaMailSender.send(message);

            log.info("END... Email send successfully!");
        } catch (MessagingException ex) {
            log.error("Email sent with error: {}",  ex.getMessage());
        }
    }
}
