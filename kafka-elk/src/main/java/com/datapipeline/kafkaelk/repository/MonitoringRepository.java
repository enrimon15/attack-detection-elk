package com.datapipeline.kafkaelk.repository;

import com.datapipeline.kafkaelk.dto.FilterDto;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface MonitoringRepository {
    void saveData(Map<String, Object> data);

    Page<Document> getPaginatedData(Pageable pageable);

    Page<Document> getFilteredData(FilterDto filterDto, Pageable pageable);
}
