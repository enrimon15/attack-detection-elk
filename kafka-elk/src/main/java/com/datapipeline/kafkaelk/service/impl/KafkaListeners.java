package com.datapipeline.kafkaelk.service.impl;

import com.datapipeline.kafkaelk.dto.PacketbeatMessage;
import com.datapipeline.kafkaelk.service.MonitoringService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
class KafkaListeners {

    /*@KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    void listener(PacketbeatMessage message) {
        log.debug(message.toString());
    }*/

    @Autowired
    private MonitoringService monitoringService;

    @KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    void listener(ConsumerRecord<String, Object> record) {
        Object messageValue = record.value();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> packetbeatData = mapper.convertValue(messageValue, new TypeReference<Map<String, Object>>(){});
        //log.debug(messageMap.toString());
        monitoringService.saveData(packetbeatData);
    }
}
