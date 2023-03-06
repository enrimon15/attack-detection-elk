package com.datapipeline.kafkaelk.service.impl;

import com.datapipeline.kafkaelk.dto.FilterDto;
import com.datapipeline.kafkaelk.repository.MonitoringRepository;
import com.datapipeline.kafkaelk.service.MonitoringService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    private MonitoringRepository monitoringRepository;

    @Override
    public void saveData(Map<String, Object> data) {
        if (data == null || data.isEmpty()) {
            // exception
        }
        monitoringRepository.saveData(data);
    }

    @Override
    public Page<Document> getData(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return monitoringRepository.getPaginatedData(pageable);
    }

    @Override
    public Page<Document> getFilteredData(FilterDto filterDto, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return monitoringRepository.getFilteredData(filterDto, pageable);
    }
}
