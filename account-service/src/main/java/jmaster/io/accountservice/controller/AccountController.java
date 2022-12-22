package jmaster.io.accountservice.controller;

import jmaster.io.accountservice.entity.AccountDTO;
import jmaster.io.accountservice.entity.MessageDTO;
import jmaster.io.accountservice.entity.StatisticDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public AccountController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/new")
    public AccountDTO create(@RequestBody AccountDTO request) {
        StatisticDTO stat = new StatisticDTO("Account: " + request.getEmail() + " is created ", new Date());

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(request.getEmail());
        messageDTO.setToName(request.getName());
        messageDTO.setSubject("Welcome to Jmaster.io");
        messageDTO.setContent("JMaster is online learning platform!");

        kafkaTemplate.send("notification", messageDTO);
        kafkaTemplate.send("statistic", stat);

        return request;
    }
}
