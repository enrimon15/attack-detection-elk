package com.datapipeline.kafkaelk.dto.mapper;

import com.datapipeline.kafkaelk.dto.PacketbeatMessage;
import com.datapipeline.kafkaelk.entity.PacketbeatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMapper {
    PacketbeatEntity toEntity(PacketbeatMessage dto);

    PacketbeatMessage toDto(PacketbeatEntity entity);
}
