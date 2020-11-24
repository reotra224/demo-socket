package com.example.demo;

import com.example.demo.model.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(String message) {
        logger.info("Message reçu: {}", message);
        Message data = new Message("TRAORE", message);
        this.messagingTemplate.convertAndSend(
                "/topic", new SimpleDateFormat("HH:mm:ss")
                        .format(new Date()) + "== " + data.toString()
        );
    }
}
