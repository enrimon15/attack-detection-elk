package com.datapipeline.kafkaelk.service.impl;

import com.datapipeline.kafkaelk.dto.PacketbeatMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class KafkaListeners {

    /*@KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    void listener(PacketbeatMessage message) {
        log.debug(message.toString());
    }*/

    @KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    void listener(ConsumerRecord<String, Object> record) {
        Object messageValue = record.value();
        log.debug(messageValue.toString());
    }
}
