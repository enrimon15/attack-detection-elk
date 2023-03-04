package com.datapipeline.kafkaelk.repository;

import com.datapipeline.kafkaelk.entity.PacketbeatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacketbeatDataRepository extends MongoRepository<PacketbeatEntity, String> {
}
