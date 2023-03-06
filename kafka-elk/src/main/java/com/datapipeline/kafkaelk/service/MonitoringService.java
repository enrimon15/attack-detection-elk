package com.datapipeline.kafkaelk.service;

import com.datapipeline.kafkaelk.dto.FilterDto;
import org.bson.Document;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface MonitoringService {
    void saveData(Map<String, Object> data);

    Page<Document> getData(int pageNumber, int pageSize);

    Page<Document> getFilteredData(FilterDto filterDto, int pageNumber, int pageSize);
}
