package com.datapipeline.kafkaelk.repository;

import com.datapipeline.kafkaelk.dto.FilterDto;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MonitoringRepositoryImpl implements MonitoringRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.packetbeat-collection}")
    private String packetbeatMessageCollection;

    private String ORDER_BY_FIELD = "lastModifiedDate";

    private MongoCollection<Document> getCollection() {
        return mongoTemplate.getCollection(packetbeatMessageCollection);
    }

    public void saveData(Map<String, Object> data) {
        getCollection().insertOne(new Document(data));
    }

    public Page<Document> getPaginatedData(Pageable pageable) {
        return this.getPaginatedAndSortedDataWithQuery(Optional.empty(), pageable);
    }

    public Page<Document> getFilteredData(FilterDto filterDto, Pageable pageable) {
        if (filterDto == null || filterDto.getFilters() == null || filterDto.getFilters().isEmpty()) {
            return this.getPaginatedData(pageable);
        }
        Query filteredQuery = createQuery(filterDto);
        return this.getPaginatedAndSortedDataWithQuery(Optional.ofNullable(filteredQuery), pageable);
    }

    private Page<Document> getPaginatedAndSortedDataWithQuery(Optional<Query> filterQuery, Pageable pageable) {
        Query queryPaginatedAndSorted = filterQuery.orElse(new Query())
                .with(pageable)
                .with(Sort.by(Sort.Direction.DESC, ORDER_BY_FIELD));
        List<Document> paginatedDocuments =
                mongoTemplate.find(queryPaginatedAndSorted, Document.class, packetbeatMessageCollection);
        long totalDocuments = getCollection().countDocuments();
        return new PageImpl<>(paginatedDocuments, pageable, totalDocuments);
    }

    public Query createQuery(FilterDto filter) {
        Criteria criteria = new Criteria();
        for (Map.Entry<String, Object> entry : filter.getFilters().entrySet()) {
            criteria.and(entry.getKey()).is(entry.getValue());
        }
        return new Query(criteria);
    }
}
