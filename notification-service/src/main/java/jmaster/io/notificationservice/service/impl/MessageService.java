package jmaster.io.notificationservice.service.impl;

import jmaster.io.notificationservice.model.MessageDTO;
import jmaster.io.notificationservice.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private final EmailService emailService;

//                GroupId: Trên kafka server sẽ lưu lại, nếu sau này chạy 2 con server thì để chùng như này, thì 2 con server sẽ cùng chung 1 group
    @KafkaListener(id = "notificationGroup", topics = "notification")
//                                            Đọc nội dung từ topic "Notification"
    public void listen(MessageDTO messageDTO) {
        log.info("Received: {}", messageDTO.getTo());
        emailService.sendEmail(messageDTO);
    }
}
