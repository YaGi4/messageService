package com.example.messageService.service;

import com.example.messageService.Dto.ShortUserInfoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class messageService {

    @SneakyThrows
    @KafkaListener(topics = "phoneMessage", groupId = "test-consumer-group")
    public void listen(String message) {
        ShortUserInfoDto shortUserInfoDto = new ObjectMapper().readValue(message,  new TypeReference<>() {});
        String SMSmessage = "Здравствуйте " + shortUserInfoDto.getUserName() + ", вы успешно зарегестрировались на нашем сайте";
        System.out.println("номер получателя  - " + shortUserInfoDto.getPhone() + "\n" + "текст сообщения - " + SMSmessage);
    }
}
